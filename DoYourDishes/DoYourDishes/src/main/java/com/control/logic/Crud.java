package com.control.logic;
import com.control.networkHttp.HttpRequestFactory;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class Crud implements TaskLogicInterface, UserLogicInterface, PlanLogicInterface {
    HttpRequestFactory httpEngine = new HttpRequestFactory();
    @Override
    public String create(int x, String planname, Plan plan) {

        return null;
    }

    @Override
    public String create(String Name, String Key) {

        return null;
    }

    @Override
    public boolean create(User user, String string1) {

        return false;
    }

    @Override
    public void read() {
        //TODO implement read
    }

    @Override
    public void update() {
        //TODO implement update
    }

    @Override
    public void delete() {
        //TODO implent delete
    }

    public String fetchData()  {
        String mem = "test";

        JSONObject response = null;

        RequestBody requestBody = new FormBody.Builder()
                .build();
        try {
            response = httpEngine.GET("https://shareyourplant.herokuapp.com/api/getData", requestBody);
            mem = response.getJSONArray("data").getJSONObject(0).getString("message");
        } catch (Exception e) {
            e.printStackTrace();
            mem = e.toString();
        }


        return mem;
    }
}
