package com.control.logic;

/**
 * The LoginController controls the functionality of the Login_Activity
 */
public interface LoginControllerInterface {

    /**
     * sending LoginRequest
     */
    void tryLogin();

    /**
     * showing a Toast on screen
     */
    void showToast(String responseText);

    /**
     * go over to HomeView when login is successful
     */
    void startHomeView(String _token, String _resUserName, String _resUserPlan, String _planName, String _planOwner);

    /**
     * reset editText field when activity is restarted
     */
    void resetData();
}
