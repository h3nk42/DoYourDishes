package com.control.networkHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequestFactory implements HTTPInterface {

    private OkHttpClient client = new OkHttpClient();
    private String test;

    @Override
    public JSONObject GET(String path, RequestBody requestBody) throws Exception {
        // TODO implement GET for http --> HIER
        //test12
        Request request = new Request.Builder()
                .url(path)
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            return jsonObject;
        } catch (IOException | JSONException e) {
            e.getLocalizedMessage();
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
            return jsonObject;
        } catch (IOException | JSONException e) {
            e.getLocalizedMessage();
            throw new Exception(e);
        }
    }

    @Override
    public JSONObject DELETE(String path, RequestBody requestBody) {
        // TODO implement POST for http
        return null;
    }
}
