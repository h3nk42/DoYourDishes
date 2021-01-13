package com.control.asyncLogic.deleteTask;

import android.os.AsyncTask;
import android.util.Log;

import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskDeleteTask extends AsyncTask<String, String, String[]> {


    private static final String TAG = "AsyncTaskDeleteTask";
    private String _token;
    private String _taskToDeleteId;
    private DeleteTaskCallback deleteTaskCallback;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";


    public AsyncTaskDeleteTask(String _token, String _taskToDeleteId, DeleteTaskCallback deleteTaskCallback) {
        this._token = _token;
        this._taskToDeleteId = _taskToDeleteId;
        this.deleteTaskCallback = deleteTaskCallback;
        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();
    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[2];
        requestBody = new FormBody.Builder()
                .add("taskId", _taskToDeleteId)
                .build();
        try {
            response = httpRequestFacade.DELETE(BackendURL + "/task/delSingleTask", requestBody, _token);
            if (response.has("data")) {
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "deleteTaskSuccess";
            } else if (response.has("customMessage")) {
                responseArr[0] = "deleteTaskError";
                responseArr[1] = response.getString("customMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "deleteTaskException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        deleteTaskCallback.deleteTaskCallBack(respArr);
    }
}
