package componentTests.networkHTTPTests;

import com.control.networkHttp.HttpRequestFacade;
import com.control.networkHttp.HttpRequestFacadeFactory;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import org.junit.Ignore;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.FormBody;


public class HTTPTests {
    private static final String TAG = "AsyncTaskLogin";

    private final String TOKENVONHARUN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImhhcnVuIiwiaWF0IjoxNjA5NzAwOTQ1LCJleHAiOjE2MDk3ODczNDV9.xxm7fhsKcKQUERAN8oHTULf8gQsoL7jjGQG9NwOMW5o";
    //private final String srcURL = "https://jsonplaceholder.typicode.com/todos";
    private final String srcURL = "https://doyourdishes.herokuapp.com/api";

    public final String getSrcURL() {
        return this.srcURL;
    }

    private JSONMemory getMemo() throws IOException {
        return new JSONMemoryImpl("FakeJSONFile.json");
    }

    @Test
    public void createJson() throws IOException {



    }

    @Ignore
    @Test
    public final void testGetTasksViaHttp() throws Exception {

        HttpRequestFacade httpFacade;
        httpFacade = HttpRequestFacadeFactory.produceHttpRequestFacade();

        FormBody requestBody = new FormBody.Builder()
                .add("userName", "harun")
                .add("password", "harun1")
                .build();


        try {
            JSONObject response = httpFacade.GET(this.srcURL + "/auth/login", requestBody, TOKENVONHARUN);         // der scheiß return mir ein JSON Objekt
            // mit enderen Worten, diese Methode build ein request

            String[] responseArr = new String[4];




            Assert.assertEquals("harun",responseArr[2]);


        }catch (Exception e){

        }



    }



}
