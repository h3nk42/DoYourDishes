package com.control.asyncLogic.addUserToPlan;

import android.os.AsyncTask;
import android.util.Log;

import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskAddUser extends AsyncTask<String, String, String[]> {
    private static final String TAG = "AsyncTaskCreatePlan";
    private String _token;
    private AddUserCallback addUserCallback;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";
    private String userNameToAdd;

    public AsyncTaskAddUser(String _token, String _userNameToAdd, AddUserCallback addUserCallback) {
        this._token = _token;
        this.userNameToAdd = _userNameToAdd;
        this.addUserCallback = addUserCallback;

        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();

    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[4];
        requestBody = new FormBody.Builder()
                .add("userName", userNameToAdd)
                .build();
        try {
            response = httpRequestFacade.POST(BackendURL + "/plan/addUser", requestBody, _token);
            if (response.has("data")) {
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "createPlanSuccess";
            } else if (response.has("customMessage")) {
                responseArr[0] = "createPlanError";
                responseArr[1] = response.getString("customMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "createPlanException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        addUserCallback.addUserCallBack(respArr);
    }
}
