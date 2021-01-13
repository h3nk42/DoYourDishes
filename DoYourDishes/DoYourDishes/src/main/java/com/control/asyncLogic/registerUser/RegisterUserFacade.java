package com.control.asyncLogic.registerUser;

/**
 * facade of the [RegisterUserCallBack] class, so methods on it get called indirectly
 */
public interface RegisterUserFacade {

    /**
     * method gets called by AsyncTask when the response came back (onPostExecute)
     *
     * @value loginData holds the values responded from HTTP  request
     */
    void registerUserCallBack(String[] loginData);

    /**
     * gets called by the controller that wants do send a [RegisterUser] Request
     *
     * @param userName         holds userName taken from EditText
     * @param password         holds password taken from EditText
     * @param registerUserUser holds the controller using the [RegisterUser] Task
     */
    void registerUserCallAsync(String userName, String password, RegisterUserUser registerUserUser);
}
