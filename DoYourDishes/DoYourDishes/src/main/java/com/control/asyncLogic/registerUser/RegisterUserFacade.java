package com.control.asyncLogic.registerUser;

public interface RegisterUserFacade {
    void registerUserCallBack(String[] loginData);

    void registerUserCallAsync(String userName, String password, RegisterUserUser registerUserUser);
}
