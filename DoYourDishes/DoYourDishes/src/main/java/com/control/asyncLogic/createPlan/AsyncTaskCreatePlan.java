package com.control.asyncLogic.createPlan;

import android.util.Log;

import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;

import okhttp3.FormBody;

class AsyncTaskCreatePlan extends android.os.AsyncTask<String,String,String[]>{

    private static final String TAG = "AsyncTaskCreatePlan";
    private String _token;
    private CreatePlanCallbackImpl createPlanCallback;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";
    private String planName;

    public AsyncTaskCreatePlan( String _token,String _planName, CreatePlanCallbackImpl createPlanCallback) {
        this._token = _token;
        this.planName = _planName;
        this.createPlanCallback = createPlanCallback;

        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();

    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[4];
        requestBody = new FormBody.Builder()
                .add("name", planName)
                .build();
        try {
            response = httpRequestFacade.POST(BackendURL + "/plan/createPlan", requestBody, _token);
            if(response.has("data")){
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "createPlanSuccess";
                responseArr[1] = response.getJSONObject("data").getString("owner");
                responseArr[2] = response.getJSONObject("data").getString("name");
                responseArr[3] = response.getJSONObject("data").getString("_id");
            } else if(response.has("customMessage")){
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
        createPlanCallback.createPlanCallBack(respArr);
    }
}