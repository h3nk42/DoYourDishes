package com.control.logic;

import android.content.Intent;
import android.widget.TextView;

import com.view.gui.HomeActivity;
import com.view.gui.MainActivity;

public class LoginController implements LoginControllerInterface{
    final TextView userNameTextView;
    final TextView passwordTextView;
    final TextView showLoginDataTextView;
    private MainActivity mainActivity;

    public LoginController(TextView _loginTextView, TextView _userNameTextView, TextView _passwordTextView, MainActivity _mainActivity ) {
        this.showLoginDataTextView = _loginTextView;
        this.userNameTextView = _userNameTextView;
        this.passwordTextView = _passwordTextView;
        this.mainActivity = _mainActivity;
    }

    @Override
    public void tryLogin() {
        AsyncTask request = new AsyncTask(userNameTextView.getText().toString(), passwordTextView.getText().toString(), "LOG_IN", this);
        request.execute();
    }

    @Override
    public void updateUi(String responseText) {
        showLoginDataTextView.setText(responseText);
    }
    @Override
    public void startHomeView(String token) {
        Intent intent = new Intent(mainActivity, HomeActivity.class);
        intent.putExtra("TOKEN", token);
        mainActivity.startActivity(intent);
    }
    @Override
    public void resetData() {
        showLoginDataTextView.setText("");
        userNameTextView.setText("");
        passwordTextView.setText("");
    }



}
