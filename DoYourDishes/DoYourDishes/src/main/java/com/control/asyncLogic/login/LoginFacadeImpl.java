package com.control.asyncLogic.login;

class LoginFacadeImpl implements LoginFacade {

    private LoginCallBack loginCallBack;

    LoginFacadeImpl (LoginCallBack _loginCallBack) {
        this.loginCallBack = _loginCallBack;
    }


    @Override
    public void loginCallAsync(String userName, String password, LoginUser loginUser) {
        this.loginCallBack.loginCallAsync(userName, password, loginUser);
    }

}
