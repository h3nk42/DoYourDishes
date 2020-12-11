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

    void startHomeView(String token);

    /**
     *  try login explanation
     */
    public void resetData();

}
