package com.control.asyncLogic;

import android.util.Log;

import com.control.networkHttp.HttpRequest;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskLogin extends android.os.AsyncTask<String,String,String[]>{


    private static final String TAG = "AsyncTaskLogin";
    private String _userName;
    private String _password;
    private LoginCallBackInterface loginCallBackInterface;
    private FormBody requestBody;
    private HttpRequest httpEngine = new HttpRequest();
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";

    public AsyncTaskLogin(String _userName, String _password, LoginCallBackInterface loginCallBackInterface) {
        this._userName = _userName;
        this._password = _password;
        this.loginCallBackInterface = loginCallBackInterface;
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
            JSONObject response = httpEngine.POST(BackendURL + "/auth/login", requestBody, "");
            Log.d(TAG, "doInBackground response: " + response);
            if (response.has("token")) {
                responseArr[0] = "loginSuccess";
                responseArr[1] = response.getString("token");
                responseArr[2] = response.getString("userName");
                responseArr[3] = response.getString("plan");
            } else {
                responseArr[0] = "loginError";
                responseArr[1] = response.getString("customMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "loginException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        loginCallBackInterface.loginCallBack(respArr);
    }
}
