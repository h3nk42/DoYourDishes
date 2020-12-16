package com.control.asyncLogic.login;

import com.control.controllerLogic.LoginController;

/**
 * this interface works with the asynchronous logIn task
 *
 *  every activity can implement its own version of the interface to handle what is done with the response Values
 *
 */
interface LoginCallBack {
    /**
     *  is called by the postExecute method of login asyncTask and receives a String[] with 1 - 4 values
     * @param loginData loginData[0] is always status of request (error or success) loginData[1] is token loginData[2] is userName loginData[3] is planId
     */
    void loginCallBack(String[] loginData);

    /**
     *  is called when the asyncTask login should be started/executed
     */
    void loginCallAsync(String userName, String password, LoginController loginActivity);
}
