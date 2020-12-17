package com.control.asyncLogic.deleteUser;

import android.os.AsyncTask;
import android.util.Log;

import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskDeleteUser extends AsyncTask<String, String, String[]> {

    private static final String TAG = "AsyncTaskDeleteUser";
    private String _token;
    private DeleteUserCallback deleteUserCallback;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";


    public AsyncTaskDeleteUser( String _token, DeleteUserCallback deleteUserCallback) {
        this._token = _token;
        this.deleteUserCallback = deleteUserCallback;
        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();
    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[2];
        requestBody = new FormBody.Builder()
                .build();
        try {
            response = httpRequestFacade.DELETE(BackendURL + "/user/delUser", requestBody, _token);
            if(response.has("data")){
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "deleteUserSuccess";
            } else if(response.has("customMessage")){
                responseArr[0] = "deleteUserError";
                responseArr[1] = response.getString("customMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "deleteUserException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        deleteUserCallback.deleteUserCallBack(respArr);
    }
}
