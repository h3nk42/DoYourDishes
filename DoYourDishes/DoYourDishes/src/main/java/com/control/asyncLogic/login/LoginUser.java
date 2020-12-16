package com.control.asyncLogic.login;

public interface LoginUser {

    public void errorCallbackLogIn(String errorInfo);

    public void successCallbackLogin (String _token, String _resUserName, String _resUserPlanId);
}
