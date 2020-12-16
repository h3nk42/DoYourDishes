package com.control.asyncLogic.login;


/**
 * Classes that want to use the AsyncTask LoginUser have to implement this interface,
 * so the asyncTask knows where to insert the response via callBacks
 */
public interface LoginUser {

    /**
     * gets called by AsyncTask when an error was responded
     * @param errorInfo holds info about the responseError
     */
    void errorCallbackLogIn(String errorInfo);

    /**
     * gets called by AsyncTask when a success was responded
     * @param _token holds the Token that got responded by http request
     * @param _resUserName  holds the Username that got responded by http request
     * @param _resUserPlanId holds the userPlanId that got responded by http request
     */
    void successCallbackLogin (String _token, String _resUserName, String _resUserPlanId);
}
