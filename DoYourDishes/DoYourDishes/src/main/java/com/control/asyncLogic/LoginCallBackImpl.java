package com.control.asyncLogic;

import com.control.controllerLogic.LoginController;


public class LoginCallBackImpl implements LoginCallBackInterface, FetchPlanCallBackInterface {

    LoginController loginActivity;
    private String userName;
    private String password;

    private String resToken;
    private String responsePlanId;
    private String respUserName;

    private String respPlanName;
    private String respPlanOwner;



    public LoginCallBackImpl(LoginController loginActivity, String userName, String password){
        this.loginActivity = loginActivity;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void loginCallBack(String[] loginData) {
        String infoField = loginData[0];
        switch(infoField){
            case("loginError"):
            case("loginException"):
                String errorMessage = loginData[1];
                loginActivity.showToast(errorMessage);
                break;
            case("loginSuccess"):
                resToken = loginData[1];
                respUserName = loginData[2];
                responsePlanId = loginData[3];
                if (responsePlanId.equals("null")) {
                    loginActivity.startHomeView(
                            resToken,
                            respUserName,
                            responsePlanId,
                            "null",
                            "null"
                    );
                    break;
                }else{
                    AsyncTaskFetchPlan request = new AsyncTaskFetchPlan(resToken, this);
                    request.execute();
                }
                break;
        }
    }

    @Override
    public void loginCallAsync(){
        AsyncTaskLogin request = new AsyncTaskLogin(userName, password, this);
        request.execute();
    }

    @Override
    public void fetchPlanCallBack(String[] loginData) {
        if(loginData[0].equals("registerSuccess")) {
            respPlanName = loginData[1];
            respPlanOwner = loginData[2];
            loginActivity.startHomeView(
                    resToken,
                    respUserName,
                    responsePlanId,
                    respPlanName,
                    respPlanOwner
            );
        }
    }

    @Override
    public void fetchPlanCallAsync() {
        // not needed in this implementation
    }
}
