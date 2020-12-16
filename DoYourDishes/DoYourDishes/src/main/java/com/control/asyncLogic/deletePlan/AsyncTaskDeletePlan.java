package com.control.asyncLogic.deletePlan;

import android.util.Log;
import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskDeletePlan extends android.os.AsyncTask<String,String,String[]>{

    private static final String TAG = "AsyncTaskDeletePlan";
    private String _token;
    private DeletePlanCallback deletePlanCallback;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";


    public AsyncTaskDeletePlan( String _token, DeletePlanCallback deletePlanCallback) {
        this._token = _token;
        this.deletePlanCallback = deletePlanCallback;
        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();
    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[4];
        requestBody = new FormBody.Builder()
                .build();
        try {
            response = httpRequestFacade.DELETE(BackendURL + "/plan/deletePlan", requestBody, _token);
            if(response.has("data")){
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "deletePlanSuccess";
            } else if(response.has("customMessage")){
                responseArr[0] = "deletePlanError";
                responseArr[1] = response.getString("customMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "deletePlanException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        deletePlanCallback.deletePlanCallBack(respArr);
    }
}
