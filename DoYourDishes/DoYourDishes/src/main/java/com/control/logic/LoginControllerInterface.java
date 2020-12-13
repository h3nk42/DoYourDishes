package com.control.logic;

public interface LoginControllerInterface {

    /**
     *  calls an AsyncLogin with the parameters taken from input fields in MainActivity
     *
     */
    public void tryLogin();

    /**
     *  Handler given to the AsyncTask
     *
     */


    void showToast(String responseText);

    void startHomeView(String _token, String _resUserName, String _resUserPlan, String _planName, String _planOwner);

    /**
     *  try login explanation
     */
    public void resetData();

}
