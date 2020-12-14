package com.control.logic;


/**
 * The LandingController controls the functionality of the Landing_Activity
 */
public interface LandingControllerInterface {

    /**
     * Implementation should start a new Register_Activity with an empty intent
     */
    void goToRegisterUser();

    /**
     * Implementation should start a new Login_Activity with an empty intent
     */
    void goToLogin();
}
