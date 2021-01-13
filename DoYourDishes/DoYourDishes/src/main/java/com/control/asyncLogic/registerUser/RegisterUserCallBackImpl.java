package com.control.asyncLogic.registerUser;

class RegisterUserCallBackImpl implements RegisterUserCallBack {

    RegisterUserUser registerUserUser;

    private String resToken;
    private String respUserName;
    private String responsePlanId;

    @Override
    public void registerUserCallBack(String[] loginData) {

        String infoField = loginData[0];
        switch (infoField) {
            case ("registerError"):
            case ("registerException"):
                String errorMessage = loginData[1];
                registerUserUser.errorCallbackRegisterUser(errorMessage);
                break;
            case ("registerSuccess"):
                this.resToken = loginData[1];
                this.respUserName = loginData[2];
                this.responsePlanId = loginData[3];
                registerUserUser.successCallbackRegisterUser(resToken, respUserName, responsePlanId);
                break;
        }
    }

    @Override
    public void registerUserCallAsync(String userName, String password, RegisterUserUser registerUserUser) {
        this.registerUserUser = registerUserUser;
        AsyncTaskRegisterUser asyncTaskRegisterUser = new AsyncTaskRegisterUser(userName, password, this);
        asyncTaskRegisterUser.execute();
    }
}
