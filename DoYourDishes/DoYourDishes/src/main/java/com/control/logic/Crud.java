package com.control.logic;

import com.control.networkHttp.Http;

public class Crud implements CrudTaskInterface,CrudUserInterface,CrudPlan{

    Http httpEngine = new Http();

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

    public String fetchData() {
        String mem = "test";
        mem = httpEngine.GET("https://shareyourplant.herokuapp.com/api/getData");
        return mem;
    }
}
