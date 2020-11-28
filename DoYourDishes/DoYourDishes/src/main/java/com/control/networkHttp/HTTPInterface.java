package com.control.networkHttp;


import org.json.JSONException;
import org.json.JSONObject;

public interface HTTPInterface {

    JSONObject GET(String path) throws Exception;

    void POST();

}
