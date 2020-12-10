package com.control.logic;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.view.gui.HomeActivity;
import com.view.gui.MainActivity;

public class LoginController implements LoginControllerInterface{
    final EditText userNameTextView;
    final EditText passwordTextView;
    final TextView showLoginDataTextView;
    private MainActivity mainActivity;
    public ControllState state;

    public LoginController(TextView _loginTextView, EditText _userNameTextView, EditText _passwordTextView, MainActivity _mainActivity ) {
        this.showLoginDataTextView = _loginTextView;
        this.userNameTextView = _userNameTextView;
        this.passwordTextView = _passwordTextView;
        this.mainActivity = _mainActivity;
        this.state = ControllState.NOT_LOGGED_IN;
    }

    @Override
    public void tryLogin() {
        AsyncTask request = new AsyncTask(userNameTextView.getText().toString(), passwordTextView.getText().toString(), "LOG_IN", this);
        request.execute();
    }

    @Override
    public void updateUi(String responseText) {
        showLoginDataTextView.setText(responseText);
        this.state = ControllState.LOG_IN_ERROR;
    }
    @Override
    public void startHomeView(String token) {
        this.state = ControllState.LOGGED_IN;
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
