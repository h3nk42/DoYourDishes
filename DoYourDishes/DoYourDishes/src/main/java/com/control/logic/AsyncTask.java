package com.control.logic;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.control.networkHttp.HttpRequest;
import com.view.gui.HomeActivity;
import com.view.gui.MainActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AsyncTask extends android.os.AsyncTask<Void, Void, Void> {
    private static final String TAG = "MyAsyncTask";

    private HashMap<String, String> stringValues = new HashMap<String, String>();


    final HttpRequest httpEngine = new HttpRequest();
    private Boolean logInError = false;
    private HomeController homeController;
    private LoginController loginController;


    public AsyncTask(String _token, String _method, HomeController _homeController) {
        stringValues.put("method", _method);
        homeController = _homeController;

        switch (stringValues.get("method")) {
            case "WHO_AM_I":

                this.stringValues.put("token", _token);
                Log.d(TAG, "AsyncTaskFactory: ");
                break;
        }
    }
    // state = ActiveState.WHOAMI;


    //AsyncLogin
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

    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          makeRequestBody()                                 //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @NotNull
    private RequestBody makeRequestBody() {
        RequestBody requestBody = null;
        switch (stringValues.get("method")) {
            case "LOG_IN":
                requestBody = new FormBody.Builder()
                        .add("userName", stringValues.get("userName"))
                        .add("password", stringValues.get("password"))
                        .build();
                break;
            case "WHO_AM_I":
                requestBody = new FormBody.Builder()
                        .build();
                break;
        }
        return requestBody;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         choose what to do doInBackround()                  //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Void doInBackground(Void... params) {
        switch (stringValues.get("method")) {
            case "LOG_IN":
                doWhenLoginBackGround();
                break;
            case "WHO_AM_I":
                doWhenWhoAmIBackground();
                break;
        }
        return null;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          doInBackround()                                   //
    ///////////////////////////////////////////////////////////////////////////////////////////////


    public Void doWhenLoginBackGround(Void... voids) {
        // https://stackoverflow.com/questions/26161538/throw-an-exception-in-doinbackground-and-catch-in-onpostexecute#:~:text=You%20cannot%20throw%20exceptions%20across,handle%20it%20in%20onPostExecute()%20.&text=No%2C%20you%20can't%20throw%20exception%20in%20the%20background%20thread.
        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        Log.d(TAG, "doWhenLoginBackGround: in");
        RequestBody requestBody = makeRequestBody();
        try {
            JSONObject response = httpEngine.POST("http://10.0.2.2:3001/api/auth/login", requestBody, "");
            Log.d(TAG, "doWhenLogin response: " + response);
            if (response.has("token")) {
                stringValues.put("responseText", response.getString("token"));
                logInError = false;
            } else {
                stringValues.put("responseText", response.getString("customMessage"));
                logInError = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            stringValues.put("responseText", e.toString());
            Log.d(TAG, "AsyncLogin: " + e.toString());
        }
        Log.d(TAG, "doWhenLoginBackGround: out");
        return null;
    }

    public Void doWhenWhoAmIBackground(Void... voids) {
        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        JSONObject response = null;
        RequestBody requestBody = makeRequestBody();
        try {
            response = httpEngine.GET("http://10.0.2.2:3001/api/auth/whoAmI", requestBody, stringValues.get("token"));
            stringValues.put("responseText", response.getJSONObject("data").getString("userName"));
        } catch (Exception e) {
            e.printStackTrace();
            stringValues.put("responseText", e.toString());
            Log.d(TAG, "AsyncWhoAmI: " + e.toString());
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //After completing execution of given task , control will return here.
        //Hence if you want to populate UI elements with fetched data, do it here
        switch (stringValues.get("method")) {
            case "LOG_IN":
                doWhenLoginPostExecute();
                break;
            case "WHO_AM_I":
                doWhenWhoAmIPostExecute();
                break;
        }

    }

    private void doWhenWhoAmIPostExecute() {
        Log.d(TAG, "doWhenWhoAmIPostExecute: in");
        homeController.updateUi(stringValues.get("responseText"));
        Log.d(TAG, "doWhenWhoAmIPostExecute: out");
    }

    private void doWhenLoginPostExecute(){
        Log.d(TAG, "doWhenLoginPostExecute: in");
        if(logInError) {
            loginController.updateUi(stringValues.get("responseText"));
        } else {
            loginController.startHomeView(stringValues.get("responseText"));
            Log.d(TAG, "onPostExecute: login: " + stringValues.get("responseText"));
        }
        Log.d(TAG, "doWhenLoginPostExecute: out");
    }
}
