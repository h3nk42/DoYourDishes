package com.control.asyncLogic.login;

/**
 * this interface works with the asynchronous logIn task
 * every activity can implement its own version of the interface to handle what is done with the response Values
 */
public interface LoginFacade {
    /**
     * is called when the asyncTask login should be started/executed
     */
    void loginCallAsync(String userName, String password, LoginUser loginUser);
}
