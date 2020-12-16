package com.control.networkHttp;

import org.json.JSONObject;

import okhttp3.RequestBody;

class HttpRequestFacadeImpl implements HttpRequestFacade {

    private HttpRequest httpRequest;

    HttpRequestFacadeImpl(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public JSONObject GET(String path, RequestBody data, String token) throws Exception {
        return httpRequest.GET(path,data,token);
    }

    @Override
    public JSONObject POST(String path, RequestBody data, String token) throws Exception {
        return httpRequest.POST(path,data,token);
    }

    @Override
    public JSONObject DELETE(String path, RequestBody data, String token) throws Exception {
        return httpRequest.DELETE(path,data,token);
    }
}
