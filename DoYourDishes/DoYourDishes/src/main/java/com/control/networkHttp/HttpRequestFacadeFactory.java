package com.control.networkHttp;

public class HttpRequestFacadeFactory {

    public static HttpRequestFacade produceHttpRequestFacade(){
        HttpRequest httpRequest = new HttpRequestImpl();
        return new HttpRequestFacadeImpl(httpRequest);
    }
}
