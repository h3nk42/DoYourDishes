package com.control.logic;

import android.widget.TextView;

import com.control.networkHttp.HttpRequestFactory;
import com.view.gui.MainActivity;

public class LoginController implements LoginControllerInterface{
    final TextView userNameTextView;
    final TextView passwordTextView;
    final TextView loginTextView;
    final HttpRequestFactory httpEngine = new HttpRequestFactory();
    private MainActivity mainActivity;
    ActiveState state;

    // TextView; TextView; TextView; Homeactivity
    public LoginController(TextView _loginTextView, TextView _userNameTextView, TextView _passwordTextView, MainActivity _mainActivity ) {
        this.loginTextView = _loginTextView;
        this.userNameTextView = _userNameTextView;
        this.passwordTextView = _passwordTextView;
        this.mainActivity = _mainActivity;
    }

    @Override
    public void tryLogin() {
        state = ActiveState.LOGIN;
        AsyncLogin request = new AsyncLogin(loginTextView, userNameTextView.getText().toString(), passwordTextView.getText().toString(), mainActivity);
        request.execute();
    }

    @Override
    public void resetData() {
        loginTextView.setText("");
        userNameTextView.setText("");
        passwordTextView.setText("");
    }



}
