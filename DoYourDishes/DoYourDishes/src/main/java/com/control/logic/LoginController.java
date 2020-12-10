package com.control.logic;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.control.networkHttp.HttpRequest;
import com.view.gui.HomeActivity;
import com.view.gui.MainActivity;

public class LoginController implements LoginControllerInterface{
    final TextView userNameTextView;
    final TextView passwordTextView;
    final TextView loginTextView;
    final HttpRequest httpEngine = new HttpRequest();
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
        //AsyncLogin request = new AsyncLogin(loginTextView, userNameTextView.getText().toString(), passwordTextView.getText().toString(), mainActivity);
        AsyncTask request = new AsyncTask(userNameTextView.getText().toString(), passwordTextView.getText().toString(), "LOG_IN", this);
        request.execute();
    }

    @Override
    public void updateUi(String responseText) {
        loginTextView.setText(responseText);
    }
    @Override
    public void startHomeView(String token) {
        Intent intent = new Intent(mainActivity, HomeActivity.class);
        intent.putExtra("TOKEN", token);
        mainActivity.startActivity(intent);
    }
    @Override
    public void resetData() {
        loginTextView.setText("");
        userNameTextView.setText("");
        passwordTextView.setText("");
    }



}
