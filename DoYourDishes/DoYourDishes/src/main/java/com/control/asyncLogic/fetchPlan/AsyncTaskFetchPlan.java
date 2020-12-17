package com.control.asyncLogic.fetchPlan;

import android.util.Log;

import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;
import com.model.dataModel.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

class AsyncTaskFetchPlan extends android.os.AsyncTask<String,String,String[]>{


    private static final String TAG = "AsyncTaskFetchPLan";
    private List<User> users;
    private String _token;
    private FetchPlanCallBackImpl fetchPlanCallBackImpl;
    private FormBody requestBody;
    private HttpRequestFacade httpRequestFacade;
    private final String BackendURL = "https://doyourdishes.herokuapp.com/api";

    public AsyncTaskFetchPlan( String _token, FetchPlanCallBackImpl fetchPlanCallBackImpl) {
        this._token = _token;
        this.fetchPlanCallBackImpl = fetchPlanCallBackImpl;

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
            response = httpRequestFacade.GET(BackendURL + "/plan/findPlanToOwner", requestBody, _token);
            if(response.has("data")){
                Log.d(TAG, "doInBackground: " + response);
                responseArr[0] = "fetchPlanSuccess";
                responseArr[1] = response.getJSONObject("data").getString("owner");
                responseArr[2] = response.getJSONObject("data").getString("name");
                String planId = response.getJSONObject("data").getString("_id");
                JSONArray userArr = response.getJSONObject("data").getJSONArray("users");
                users = new ArrayList<User>();
                for(int i = 0; i < userArr.length(); i++){
                    JSONObject tempUser = userArr.getJSONObject(i);
                    User newUser = new User(tempUser.getString("userName"), planId, tempUser.getInt("points"));
                    users.add(newUser);
                    Log.d(TAG, "doInBackground: " + newUser);
                }
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
        fetchPlanCallBackImpl.fetchPlanCallBack(respArr, users);
    }
}
