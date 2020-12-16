package com.control.asyncLogic.registerUser;

import com.control.asyncLogic.login.LoginUser;

interface RegisterUserCallBack {

    void registerUserCallBack(String[] loginData);

    /**
     *  is called when the asyncTask login should be started/executed
     */
    void registerUserCallAsync(String userName, String password, RegisterUserUser registerUserUser);
}
