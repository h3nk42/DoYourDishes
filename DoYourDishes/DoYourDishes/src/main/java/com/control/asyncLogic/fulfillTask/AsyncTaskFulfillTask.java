package com.control.asyncLogic.fulfillTask;

import android.os.AsyncTask;
import android.util.Log;

import com.control.asyncLogic.deleteTask.DeleteTaskCallback;
import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskFulfillTask extends AsyncTask<String,String,String[]> {

    private static final String TAG = "AsyncTaskDeleteTask";
    private String _token;
    private String _taskToFulfillId;
    private FulfillTaskCallback fulfillTaskCallback;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";


    public AsyncTaskFulfillTask( String _token, String _taskToFulfillId, FulfillTaskCallback fulfillTaskCallback) {
        this._token = _token;
        this._taskToFulfillId = _taskToFulfillId;
        this.fulfillTaskCallback = fulfillTaskCallback;
        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();
    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[2];
        requestBody = new FormBody.Builder()
                .add("taskId", _taskToFulfillId)
                .build();
        try {
            response = httpRequestFacade.POST(BackendURL + "/task/fulfillTask", requestBody, _token);
            if(response.has("data")){
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "fulfillTaskSuccess";
            } else if(response.has("customMessage")){
                responseArr[0] = "fulfillTaskError";
                responseArr[1] = response.getString("customMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "fulfillTaskException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        fulfillTaskCallback.fulfillTaskCallBack(respArr);
    }
}
