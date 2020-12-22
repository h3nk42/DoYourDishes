package com.control.asyncLogic.login;
import android.util.Log;


import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;


import okhttp3.FormBody;

class AsyncTaskLogin extends android.os.AsyncTask<String,String,String[]>{


    private static final String TAG = "AsyncTaskLogin";
    private String _userName;
    private String _password;
    private LoginCallBackImpl loginCallBackImpl;
    private FormBody requestBody;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";
    private HttpRequestFacade httpRequestFacade;

    public AsyncTaskLogin(String _userName, String _password, LoginCallBackImpl loginCallBackImpl) {
        this._userName = _userName;
        this._password = _password;
        this.loginCallBackImpl = loginCallBackImpl;

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
            JSONObject response = httpRequestFacade.POST(BackendURL + "/auth/login", requestBody, "");
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
        }
        catch (Exception e) {
           // e.printStackTrace();
            responseArr[0] = "loginException";
            if(e.toString().startsWith("java.lang.Exception: java.net.SocketTimeoutException: timeout"))
            {
                responseArr[1] = "server needed to wake up! try again :)";
                return responseArr;
            }
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground generalE: " + e.toString());
            return responseArr;
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        loginCallBackImpl.loginCallBack(respArr);
    }
}
