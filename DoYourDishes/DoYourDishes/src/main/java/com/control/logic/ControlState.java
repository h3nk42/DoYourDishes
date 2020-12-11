package com.control.logic;

public enum ControlState {

    LOGGED_IN,
    LOG_IN_ERROR,
    NOT_LOGGED_IN,

    CREATE_USER,             //Username:String  password:String
    DELETE_USER,             //Username:String

    LOGIN,                 //Username:String  password:String
    WHOAMI,

    CREATE_PLAN,
    DELETE_PLAN,

    ADD_USER,
    FIND_PLAN,
    CREATE_TASK,
    DELETE_SINGLE_TASK,
    FULFILL_TASK

    }