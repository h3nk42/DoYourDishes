package com.control.logic;

public interface CrudTaskInterface {

    String create(int x, String planname, Plan plan);

    void read();

    void update();

    void delete();


}