package com.control.asyncLogic.registerUser;


public class RegisterUserFacadeFactory {

    public static RegisterUserFacade produceRegisterUserFacade() {
        RegisterUserCallBack registerUserCallBack = new RegisterUserCallBackImpl();
        return new RegisterUserFacadeImpl(registerUserCallBack);
    }
}
