package com.control.asyncLogic;

import android.util.Log;

import com.control.controllerLogic.HomeController;
import com.control.controllerLogic.LoginController;
import com.control.controllerLogic.RegisterController;
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

    private Boolean responseError = false;
    private Boolean exceptionThrown = false;

    private Boolean userIsInPlan = false;

    private HomeController homeController;
    private LoginController loginController;
    private RegisterController registerController;
    private String method;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";


    public AsyncTask(String _token, String _method, String _planName, HomeController _homeController) {
        method = _method;
        stringValues.put("method", _method);
        homeController = _homeController;
        switch (method) {
            case "CREATE_PLAN":
                this.stringValues.put("token", _token);
                this.stringValues.put("planName", _planName);
                Log.d(TAG, "AsyncTask: " + _method + " constructor");
                break;
        }
    }

    public AsyncTask(String _userName, String _password, String _method, LoginController _loginController) {
        method = _method;
        loginController = _loginController;
        switch (method) {
            case "LOG_IN":
                stringValues.put("userName", _userName);
                stringValues.put("password", _password);
                break;
        }
    }




    public AsyncTask(String _userName, String _password, String _method, RegisterController _registerController) {
        method = _method;
        registerController = _registerController;
        switch (method) {
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
            switch (method) {
                case "CREATE_PLAN":
                    requestBody = new FormBody.Builder()
                            .add("name", stringValues.get("planName"))
                            .build();
                    break;
                case "REGISTER_USER":
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
        switch (method) {
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
        Log.d(TAG, "doWhenFetchMyPLanBackGround: in");
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
            Log.d(TAG, "doWhenFetchMyPLanBackGround e: " + e.toString());
        }
        Log.d(TAG, "doWhenFetchMyPLanBackGround: out");
    }

    public void  doWhenLoginBackGround(Void... voids) {
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
                responseError = false;
                // falls user nicht in plan ist:
                if(stringValues.get("responseUserPlanId").equals("null")){
                   //
                }else {
                    userIsInPlan = true;
                    doWhenFetchMyPLanBackGround();
                }
            } else {
                stringValues.put("responseErrorMessage", response.getString("customMessage"));
                responseError = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
            stringValues.put("exceptionResponse", e.toString());
            Log.d(TAG, "doWhenLoginBackGround e: " + e.toString());
        }
        Log.d(TAG, "doWhenLoginBackGround: out");
    }



    private void doWhenRegisterUserBackground(){
        Log.d(TAG, "doWhenRegisterUserBackground: in");
        JSONObject response = null;
        RequestBody requestBody = makeRequestBody(false);
        try {
            response = httpEngine.POST(BackendURL + "/user/createUser", requestBody, "");
            if (response.has("customMessage")) {
                stringValues.put("responseErrorMessage", response.getString("customMessage"));
                responseError = true;
            } else {
                stringValues.put("responseText", "user created!");
                responseError = false;
                doWhenLoginBackGround();
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
            stringValues.put("exceptionResponse", e.toString());
            Log.d(TAG, "doWhenRegisterUserBackground: e: " + e.toString());
        }
        Log.d(TAG, "doWhenRegisterUserBackground: out");
    }

    private void doWhenCreatePlanBackground(){
        Log.d(TAG, "doWhenCreatePlanBackground: in");
        JSONObject response = null;
        RequestBody requestBody = makeRequestBody(false);
        try {
            response = httpEngine.POST(BackendURL + "/plan/createPlan", requestBody, stringValues.get("token"));
            if (response.has("customMessage")) {
                stringValues.put("responseText", response.getString("customMessage"));
                responseError = true;
            } else {
                stringValues.put("responsePlanName", response.getJSONObject("data").getString("name"));
                stringValues.put("responsePlanId", response.getJSONObject("data").getString("_id"));
                stringValues.put("responsePlanOwner", response.getJSONObject("data").getString("owner"));
                responseError = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionThrown = true;
            stringValues.put("exceptionResponse", e.toString());
            Log.d(TAG, "doWhenCreatePlanBackground e:" + e.toString());
        }
        Log.d(TAG, "doWhenCreatePlanBackground: out");
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        switch (method) {
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
        } else if (responseError) {
            registerController.showToast(stringValues.get("responseErrorMessage"));
        } else {
            String resUserName = stringValues.get("responseUserName");
            String resUserPlanId = stringValues.get("responseUserPlanId");
            String resToken = stringValues.get("responseToken");
        registerController.startHomeView(
                resToken,
                resUserName,
                resUserPlanId,
                "null",
                "null"
        );
        Log.d(TAG, "doWhenRegisterUserPostExecute: " + stringValues.get("token"));
        }
        Log.d(TAG, "doWhenRegisterUserPostExecute: out");
    }

    private void doWhenLoginPostExecute() {
        Log.d(TAG, "doWhenLoginPostExecute: in");
        String responseUserName = stringValues.get("responseUserName");
        String responseUserPlanId = stringValues.get("responseUserPlanId");
        String responseToken = stringValues.get("responseToken");
        if (exceptionThrown) {
            loginController.showToast("network error");
        } else if (responseError) {
            String responseErrorMessage = stringValues.get("responseErrorMessage");
            loginController.showToast(responseErrorMessage);
        } else {
            if(userIsInPlan) {
                String responsePlanName = stringValues.get("responsePlanName");
                String responsePlanOwner = stringValues.get("responsePlanOwner");
                loginController.startHomeView(
                        responseToken,
                        responseUserName,
                        responseUserPlanId,
                        responsePlanName,
                        responsePlanOwner
                );
            } else {
                loginController.startHomeView(
                        responseToken,
                        responseUserName,
                        responseUserPlanId,
                        "null",
                        "null"
                );
                Log.d(TAG, "onPostExecute: login: " + stringValues.get("token"));
            }
        }
        Log.d(TAG, "doWhenLoginPostExecute: out");
    }

    private void doWhenCreatePlanPostExecute(){
        Log.d(TAG, "doWhenCreatePlanPostExecute: in");
        String responsePlanName = stringValues.get("responsePlanName");
        String responsePlanId = stringValues.get("responsePlanId");
        String responsePlanOwner = stringValues.get("responsePlanOwner");
        if (exceptionThrown) {
            homeController.showToast("network error");
        }  else if (responseError) {
            homeController.showToast(stringValues.get("responseText"));
        } else {
            homeController.callBackCreatedPlan(
                    responsePlanOwner,
                    responsePlanName,
                    responsePlanId
            );
            Log.d(TAG, "doWhenCreatePlanPostExecute: login: " + stringValues.get("responseText"));
        }
    }
}
