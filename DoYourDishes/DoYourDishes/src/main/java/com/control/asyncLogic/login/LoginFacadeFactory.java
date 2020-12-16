package com.control.asyncLogic.login;

import com.control.controllerLogic.LoginController;

public class LoginFacadeFactory {

    public static LoginFacade produceLoginFacade(){
        LoginCallBack loginCallBack = new LoginCallBackImpl();
        return new LoginFacadeImpl(loginCallBack);
    }
}
