package com.control.asyncLogic.login;

public interface LoginUser {

    void errorCallbackLogIn(String errorInfo);

    void successCallbackLogin (String _token, String _resUserName, String _resUserPlanId);
}
