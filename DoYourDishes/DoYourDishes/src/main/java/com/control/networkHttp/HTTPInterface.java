package com.control.networkHttp;


import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;

public interface HTTPInterface {
    JSONObject GET(String path, RequestBody data) throws Exception;
    JSONObject POST(String path, RequestBody data, String token) throws Exception;
    JSONObject DELETE(String path, RequestBody data) throws Exception;

}
