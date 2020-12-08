package com.control.networkHttp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequestFactory implements HTTPInterface {
    private static final String TAG="httpFactory";

    private OkHttpClient client = new OkHttpClient();
    private String test;

    @Override
    public JSONObject GET(String path, RequestBody requestBody, String token) throws Exception {
        // TODO implement GET for http --> HIER

        Request request = new Request.Builder()
                .url(path)
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            Log.d(TAG, "GET jsondata: " + jsonData );
            Log.d(TAG, "GET token: " + token );
            JSONObject jsonObject = new JSONObject(jsonData);
            return jsonObject;
        } catch (IOException | JSONException e) {
            e.getLocalizedMessage();
            Log.d(TAG, "GET: " + e );
            throw new Exception(e);
        }
    }

    @Override
    public JSONObject POST(String path, RequestBody requestBody, String token) throws Exception {
        // TODO implement POST for http
        Request request = new Request.Builder()
                .url(path)
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            Log.d(TAG, "POST: " + jsonObject );
            return jsonObject;
        } catch (IOException | JSONException e) {
            Log.d(TAG, "POST: ttttest");
            e.getLocalizedMessage();
            throw new Exception(e);
        }
    }

    @Override
    public JSONObject DELETE(String path, RequestBody requestBody, String token) {
        // TODO implement POST for http
        return null;
    }
}
