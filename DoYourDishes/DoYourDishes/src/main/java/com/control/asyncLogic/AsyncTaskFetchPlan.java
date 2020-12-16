package com.control.asyncLogic;

import android.util.Log;

import com.control.networkHttp.HttpRequest;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskFetchPlan extends android.os.AsyncTask<String,String,String[]>{


    private static final String TAG = "AsyncTaskFetchPLan";
    private String _token;
    private FetchPlanCallBackInterface fetchPlanCallBackInterface;
    private FormBody requestBody;
    private HttpRequest httpEngine = new HttpRequest();
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";

    public AsyncTaskFetchPlan( String _token, FetchPlanCallBackInterface fetchPlanCallBackInterface) {
        this._token = _token;
        this.fetchPlanCallBackInterface = fetchPlanCallBackInterface;
    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[4];
        requestBody = new FormBody.Builder()
                .build();
        try {
            response = httpEngine.GET(BackendURL + "/plan/findPlanToOwner", requestBody, _token);
            if(response.has("data")){
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "fetchPlanSuccess";
                responseArr[1] = response.getJSONObject("data").getString("owner");
                responseArr[2] = response.getJSONObject("data").getString("name");
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
    protected void onPostExecute(String[] respArr) {
        fetchPlanCallBackInterface.fetchPlanCallBack(respArr);
    }
}
