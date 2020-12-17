package com.control.controllerLogic.RegisterLogic;

public interface RegisterControllerInterface {

    /**
     * sending register request
     */
    void registerUser();

    /**
     * showing a Toast on screen
     */
    void showToast(String responseText);

    /**
     * go over to HomeView when register is successful
     */
    void startHomeActivity(String _token, String _resUserName, String _resUserPlanId, String _planName, String _planOwner);
}
