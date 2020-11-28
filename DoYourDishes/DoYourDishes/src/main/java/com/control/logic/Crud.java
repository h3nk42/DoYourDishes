package com.control.logic;

public class Crud implements CrudTaskInterface,CrudUserInterface,CrudPlan{
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
}
