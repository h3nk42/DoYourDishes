package com.control.asyncLogic.registerUser;

/**
 * Classes that want to use the AsyncTask [RegisterUser] have to implement this interface,
 * so the asyncTask knows where to insert the response via callBacks
 */
public interface RegisterUserUser {

        /**
         *             gets called by AsyncTask when an error was responded
         * @param errorInfo holds info about the responseError
         */
        void errorCallbackRegisterUser(String errorInfo);


        /**
         * gets called by AsyncTask when a success was responded
         * @param _token holds the Token that got responded by http request
         * @param _resUserName  holds the Username that got responded by http request
         * @param _resUserPlanId holds the userPlanId that got responded by http request
         */
        void successCallbackRegisterUser (String _token, String _resUserName, String _resUserPlanId);

}
