package com.control.asyncLogic.registerUser;

public interface RegisterUserUser {
        void errorCallbackRegisterUser(String errorInfo);

        void successCallbackRegisterUser (String _token, String _resUserName, String _resUserPlanId);

}
