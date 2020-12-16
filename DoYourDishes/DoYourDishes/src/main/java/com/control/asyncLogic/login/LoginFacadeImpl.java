package com.control.asyncLogic.login;

import android.util.Log;

import com.control.controllerLogic.LoginController;

class LoginFacadeImpl implements LoginFacade {

    private LoginCallBack loginCallBack;

    LoginFacadeImpl (LoginCallBack _loginCallBack) {
        this.loginCallBack = _loginCallBack;
    }

    @Override
    public void loginCallBack(String[] loginData) {
        this.loginCallBack.loginCallBack(loginData);
    }

    @Override
    public void loginCallAsync(String userName, String password, LoginController loginController) {
        this.loginCallBack.loginCallAsync(userName, password, loginController);
    }

}
