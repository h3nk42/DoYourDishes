package com.control.asyncLogic.login;

public class LoginFacadeFactory {
    public static LoginFacade produceLoginFacade() {
        LoginCallBack loginCallBack = new LoginCallBackImpl();
        return new LoginFacadeImpl(loginCallBack);
    }
}
