package com.control.asyncLogic.login;


class LoginCallBackImpl implements LoginCallBack {

    LoginUser loginUser;
    private String userName;
    private String password;

    private String resToken;
    private String responsePlanId;
    private String respUserName;



    @Override
    public void loginCallBack(String[] loginData) {
        String infoField = loginData[0];
        switch(infoField){
            case("loginError"):
            case("loginException"):
                String errorMessage = loginData[1];
                loginUser.errorCallbackLogIn(errorMessage);
                break;
            case("loginSuccess"):
                resToken = loginData[1];
                respUserName = loginData[2];
                responsePlanId = loginData[3];
                loginUser.successCallbackLogin(resToken, respUserName, responsePlanId);
                break;
        }
    }

    @Override
    public void loginCallAsync(String userName, String password, LoginUser loginUser){
        this.loginUser = loginUser;
        this.userName = userName;
        this.password = password;

        AsyncTaskLogin request = new AsyncTaskLogin(userName, password, new LoginFacadeImpl(this));
        request.execute();
    }

}
