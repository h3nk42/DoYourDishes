package com.control.controllerLogic.LoginLogic;

/**
 * The LoginController controls the functionality of the Login_Activity
 */
public interface LoginControllerInterface {

    /**
     * sending LoginRequest
     */
    void tryLogin();

    void startHomeActivity(String _token, String _resUserName, String _resUserPlanId, String _planName, String _planOwner);

    /**
     * reset editText field when activity is restarted
     */
    void resetData();
}
