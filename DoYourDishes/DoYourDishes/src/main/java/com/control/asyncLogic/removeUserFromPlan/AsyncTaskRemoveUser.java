package com.control.asyncLogic.removeUserFromPlan;

import android.os.AsyncTask;
import android.util.Log;

import com.control.asyncLogic.deleteUser.DeleteUserCallback;
import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskRemoveUser extends AsyncTask<String,String,String[]> {


    private static final String TAG = "AsyncTaskRemoveUser";
    private String _token;
    private String _userNameToRemove;
    private RemoveUserCallback removeUserCallback;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";


    public AsyncTaskRemoveUser( String _token,String _userNameToRemove, RemoveUserCallback removeUserCallback) {
        this._token = _token;
        this._userNameToRemove = _userNameToRemove;
        this.removeUserCallback = removeUserCallback;
        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();
    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[2];
        requestBody = new FormBody.Builder()
                .add("userName", _userNameToRemove)
                .build();
        try {
            Log.d(TAG, "doInBackground: Token: "+ _token);
            response = httpRequestFacade.POST(BackendURL + "/plan/removeUser", requestBody, _token);
            if(response.has("data")){
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "removeUserSuccess";
            } else if(response.has("customMessage")){
                responseArr[0] = "removeUserError";
                responseArr[1] = response.getString("customMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "removeUserException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        removeUserCallback.removeUserCallBack(respArr);
    }
}
