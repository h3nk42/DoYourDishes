package com.control.asyncLogic.registerUser;


class RegisterUserFacadeImpl implements RegisterUserFacade {

    private RegisterUserCallBack registerUserCallBack;

    RegisterUserFacadeImpl(RegisterUserCallBack registerUserCallBack) {
        this.registerUserCallBack = registerUserCallBack;
    }

    @Override
    public void registerUserCallBack(String[] loginData) {
        this.registerUserCallBack.registerUserCallBack(loginData);
    }

    @Override
    public void registerUserCallAsync(String userName, String password, RegisterUserUser registerUserUser) {
        this.registerUserCallBack.registerUserCallAsync(userName, password, registerUserUser);
    }
}
