package com.control.networkHttp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Http implements HTTPInterface {

    private OkHttpClient client = new OkHttpClient();

    private String test;



    //constructor takes path?

    @Override
    public String GET(String path) {
        // TODO implement GET for http --> HIER

        Request request = new Request.Builder()
                .url(path)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

    @Override
    public void POST() {
        // TODO implement POST for http
    }


}
