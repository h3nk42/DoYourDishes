package com.control.asyncLogic.addTaskToPlan;

import android.os.AsyncTask;
import android.util.Log;

import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;

import org.json.JSONObject;

import okhttp3.FormBody;

public class AsyncTaskAddTask extends AsyncTask<String, String, String[]> {
    private static final String TAG = "AsyncTaskCreatePlan";
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";
    private final String _token;
    private final String taskPointsWorth;
    private final AddTaskCallback addTaskCallback;
    private FormBody requestBody;
    private final HttpRequestFacade httpRequestFacade;
    private final String taskNameToAdd;

    public AsyncTaskAddTask(String _token, String _taskNameToAdd, String _taskPointsWorth, AddTaskCallback addTaskCallback) {
        this._token = _token;
        this.taskNameToAdd = _taskNameToAdd;
        this.taskPointsWorth = _taskPointsWorth;
        this.addTaskCallback = addTaskCallback;

        this.httpRequestFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();

    }

    @Override
    protected String[] doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: in");
        JSONObject response = null;
        String[] responseArr = new String[4];
        requestBody = new FormBody.Builder()
                .add("name", taskNameToAdd)
                .add("pointsWorth", taskPointsWorth)
                .build();
        try {
            response = httpRequestFacade.POST(BackendURL + "/task/createTask", requestBody, _token);
            if (response.has("data")) {
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "addTaskSuccess";
            } else if (response.has("customMessage")) {
                responseArr[0] = "addTaskError";
                responseArr[1] = response.getString("customMessage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseArr[0] = "addTaskException";
            responseArr[1] = e.toString();
            Log.d(TAG, "doInBackground e: " + e.toString());
        }
        Log.d(TAG, "doInBackground: out");
        return responseArr;
    }

    @Override
    protected void onPostExecute(String[] respArr) {
        addTaskCallback.addTaskCallBack(respArr);
    }
}
