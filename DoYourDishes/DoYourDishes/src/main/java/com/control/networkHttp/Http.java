package com.control.networkHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Http implements HTTPInterface {

    private OkHttpClient client = new OkHttpClient();

    private String test;


    //constructor takes path?

    @Override
    public JSONObject GET(String path) throws Exception {
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
    public void POST() {
        // TODO implement POST for http
    }


}
