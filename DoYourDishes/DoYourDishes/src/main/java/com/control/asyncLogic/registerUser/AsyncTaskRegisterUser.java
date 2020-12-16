package com.control.asyncLogic.registerUser;

import android.util.Log;

import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;


import org.json.JSONObject;

import okhttp3.FormBody;

class AsyncTaskRegisterUser extends android.os.AsyncTask<String,String,String[]> {

    private static final String TAG = "AsyncTaskRegisterUser";
    private RegisterUserCallBackImpl registerUserCallBackImpl;
    private String _userName;
    private String _password;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL =  "https://doyourdishes.herokuapp.com/api";

    public AsyncTaskRegisterUser(String _userName, String _password, RegisterUserCallBackImpl registerUserCallBackImpl) {
        this._userName = _userName;
        this._password = _password;
        this.registerUserCallBackImpl = registerUserCallBackImpl;

        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();

    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        String[] responseArr = new String[4];
        requestBody = new FormBody.Builder()
                .add("userName", _userName)
                .add("password", _password)
                .build();
        try {
            JSONObject response = httpRequestFacade.POST(BackendURL + "/user/createUser", requestBody, "");
            if (response.has("data")) {
                responseArr[0] = "registerSuccess";
                Log.d(TAG, "doInBackground: " + response.getJSONObject("data").getJSONObject("user"));
                responseArr[1] = response.getJSONObject("data").getString("token");
                responseArr[2] = response.getJSONObject("data").getJSONObject("user").getString("userName");
                responseArr[3] = response.getJSONObject("data").getJSONObject("user").getString("plan");

                Log.d(TAG, "doInBackground: " + responseArr[1]);
                Log.d(TAG, "doInBackground: " + responseArr[2]);
                Log.d(TAG, "doInBackground: " + responseArr[3]);

            } else {
                responseArr[0] = "registerError";
                responseArr[1] = response.getString("customMessage");

            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "registerException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] registerData){
        registerUserCallBackImpl.registerUserCallBack(registerData);
    }
}
