package com.control.logic;

import android.util.Log;

import com.control.networkHttp.HttpRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AsyncTask extends android.os.AsyncTask<Void, Void, Void> {
    private static final String TAG = "MyAsyncTask";
    final HttpRequest httpEngine = new HttpRequest();
    private final HashMap<String, String> stringValues = new HashMap<String, String>();
    private Boolean logInError = false;
    private Boolean exceptionThrown = false;
    private Boolean registerError = false;
    private Boolean userIsInPlan = false;


    private HomeController homeController;
    private LoginController loginController;
    private RegisterController registerController;

    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";


    public AsyncTask(String _token, String _method, String _planName, HomeController _homeController) {
        stringValues.put("method", _method);
        homeController = _homeController;
        switch (stringValues.get("method")) {
            case "CREATE_PLAN":
                this.stringValues.put("token", _token);
                this.stringValues.put("planName", _planName);
                Log.d(TAG, "AsyncTask: " + _method + " constructor");
                break;
        }
    }

    public AsyncTask(String _userName, String _password, String _method, LoginController _loginController) {
        stringValues.put("method", _method);
        loginController = _loginController;

        switch (stringValues.get("method")) {
            case "LOG_IN":

                stringValues.put("userName", _userName);
                stringValues.put("password", _password);
                break;
        }
    }

    //AsyncLogin
    public AsyncTask(String _userName, String _password, String _method, RegisterController _registerController) {
        stringValues.put("method", _method);
        registerController = _registerController;

        switch (stringValues.get("method")) {
            case "REGISTER_USER":
                stringValues.put("userName", _userName);
                stringValues.put("password", _password);
                break;
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          makeRequestBody()                                 //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @NotNull
    private RequestBody makeRequestBody(boolean emptyBody) {
        RequestBody requestBody = null;
        if(!emptyBody) {
            switch (stringValues.get("method")) {
                case "CREATE_PLAN":
                    requestBody = new FormBody.Builder()
                            .add("name", stringValues.get("planName"))
                            .build();
                    break;
                case "REGISTER_USER":
                    requestBody = new FormBody.Builder()
                            .add("userName", stringValues.get("userName"))
                            .add("password", stringValues.get("password"))
                            .build();
                    break;
                case "LOG_IN":
                    requestBody = new FormBody.Builder()
                            .add("userName", stringValues.get("userName"))
                            .add("password", stringValues.get("password"))
                            .build();
                    break;
            }
        }else {
                requestBody = new FormBody.Builder()
                        .build();
        }
        return requestBody;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         choose what to do doInBackround()                  //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Void doInBackground(Void... params) {
        switch (stringValues.get("method")) {
            case "CREATE_PLAN":
                doWhenCreatePlanBackground();
                break;
            case "REGISTER_USER":
                doWhenRegisterUserBackground();
                break;
            case "LOG_IN":
                doWhenLoginBackGround();
                break;
        }
        return null;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          doInBackround()                                   //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void doWhenFetchMyPLanBackGround(Void... voids) {
        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        JSONObject response = null;
        RequestBody requestBody = makeRequestBody(true);
        try {
            response = httpEngine.GET(BackendURL + "/plan/findPlanToOwner", requestBody, stringValues.get("token"));
            if(response.has("data")){
                Log.d(TAG, "doWhenFetchMyPLanBackGround: " + response);
                stringValues.put("responsePlanOwner", response.getJSONObject("data").getString("owner"));
                stringValues.put("responsePlanName", response.getJSONObject("data").getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
            stringValues.put("exceptionResponse", e.toString());
            Log.d(TAG, "doWhenWhoAmIBackground exception: " + e.toString());
        }
    }


    public void  doWhenLoginBackGround(Void... voids) {
        // https://stackoverflow.com/questions/26161538/throw-an-exception-in-doinbackground-and-catch-in-onpostexecute#:~:text=You%20cannot%20throw%20exceptions%20across,handle%20it%20in%20onPostExecute()%20.&text=No%2C%20you%20can't%20throw%20exception%20in%20the%20background%20thread.
        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        Log.d(TAG, "doWhenLoginBackGround: in");
        RequestBody requestBody = makeRequestBody(false);
        try {
            JSONObject response = httpEngine.POST(BackendURL + "/auth/login", requestBody, "");
            Log.d(TAG, "doWhenLogin response: " + response);
            if (response.has("token")) {
                stringValues.put("responseToken", response.getString("token"));
                stringValues.put("token", response.getString("token"));
                stringValues.put("responseUserName", response.getString("userName"));
                stringValues.put("responseUserPlanId", response.getString("plan"));
                logInError = false;
                // falls user in plan ist:
                if(stringValues.get("responseUserPlanId").equals("null")){
                   //
                }else {
                    userIsInPlan = true;
                    doWhenFetchMyPLanBackGround();
                }
            } else {
                stringValues.put("responseErrorMessage", response.getString("customMessage"));
                logInError = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
            stringValues.put("exceptionResponse", e.toString());
            Log.d(TAG, "AsyncLogin: " + e.toString());
        }
        Log.d(TAG, "doWhenLoginBackGround: out");
    }



    private void doWhenRegisterUserBackground(){
        Log.d(TAG, "doWhenRegisterUserBackground: in");

        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        JSONObject response = null;
        RequestBody requestBody = makeRequestBody(false);
        try {
            response = httpEngine.POST(BackendURL + "/user/createUser", requestBody, "");
            if (response.has("customMessage")) {
                stringValues.put("responseErrorMessage", response.getString("customMessage"));
                registerError = true;
            } else {
                stringValues.put("responseText", "user created!");
                registerError = false;
                doWhenLoginBackGround();
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
            stringValues.put("exceptionResponse", e.toString());
            Log.d(TAG, "AsyncWhoAmI: " + e.toString());
        }
        Log.d(TAG, "doWhenRegisterUserBackground: out");
    }

    private void doWhenCreatePlanBackground(){
        Log.d(TAG, "doWhenCreatePlanBackground: in");

        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        JSONObject response = null;
        RequestBody requestBody = makeRequestBody(false);
        try {
            response = httpEngine.POST(BackendURL + "/plan/createPlan", requestBody, stringValues.get("token"));
            if (response.has("customMessage")) {
                stringValues.put("responseText", response.getString("customMessage"));
                registerError = true;
            } else {
                stringValues.put("planName", response.getJSONObject("data").getString("name"));
                stringValues.put("planId", response.getJSONObject("data").getString("_id"));
                stringValues.put("planOwner", response.getJSONObject("data").getString("owner"));
                registerError = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
            stringValues.put("exceptionResponse", e.toString());
            Log.d(TAG, "AsyncWhoAmI: " + e.toString());
        }
        Log.d(TAG, "doWhenCreatePlanBackground: out");
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //After completing execution of given task , control will return here.
        //Hence if you want to populate UI elements with fetched data, do it here
        switch (stringValues.get("method")) {
            case "CREATE_PLAN":
                doWhenCreatePlanPostExecute();
                break;
            case "REGISTER_USER":
                doWhenRegisterUserPostExecute();
                break;
            case "LOG_IN":
                doWhenLoginPostExecute();
                break;
        }

    }
    private void doWhenRegisterUserPostExecute() {
        Log.d(TAG, "doWhenRegisterUserPostExecute: in");
        if (exceptionThrown) {
            registerController.showToast("network error");
        } else if (registerError) {
            registerController.showToast(stringValues.get("responseErrorMessage"));
        } else {
            String resUserName = stringValues.get("responseUserName");
            String resUserPlanId = stringValues.get("responseUserPlanId");
            String resToken = stringValues.get("responseToken");
        registerController.startHomeView(resToken, resUserName, resUserPlanId, "null", "null");
        Log.d(TAG, "onPostExecute: registerUser: " + stringValues.get("token"));
        }
    }



    private void doWhenLoginPostExecute() {
        Log.d(TAG, "doWhenLoginPostExecute: in");
        String resUserName = stringValues.get("responseUserName");
        String resUserPlanId = stringValues.get("responseUserPlanId");
        String resToken = stringValues.get("responseToken");
        if (exceptionThrown) {
            loginController.showToast("network error");
        } else if (logInError) {
            String resError = stringValues.get("responseErrorMessage");
            loginController.showToast(resError);
        } else {
            if(userIsInPlan) {
                String planName = stringValues.get("responsePlanName");
                String planOwner = stringValues.get("responsePlanOwner");
                loginController.startHomeView(resToken, resUserName, resUserPlanId, planName, planOwner);
            } else {
                loginController.startHomeView(resToken, resUserName, resUserPlanId, "null", "null");
                Log.d(TAG, "onPostExecute: login: " + stringValues.get("token"));
            }
        }
        Log.d(TAG, "doWhenLoginPostExecute: out");
    }

    private void doWhenCreatePlanPostExecute(){
        Log.d(TAG, "doWhenCreatePlanPostExecute: in");
        String planName = stringValues.get("planName");
        String planId = stringValues.get("planId");
        String planOwner = stringValues.get("planOwner");
        if (exceptionThrown) {
            homeController.showToast("network error");
        }  else if (registerError) {
            homeController.showToast(stringValues.get("responseText"));
        } else {
            homeController.callBackCreatedPlan(planOwner,planName,planId);
            Log.d(TAG, "doWhenCreatePlanPostExecute: login: " + stringValues.get("responseText"));
        }
    }
}
