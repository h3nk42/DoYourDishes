package com.control.logic;

import android.widget.TextView;

import com.control.networkHttp.HttpRequestFactory;
import com.view.gui.MainActivity;

public class Login implements LoginLogicInterface {

    final TextView userNameTextView;
    final TextView passwordTextView;
    final TextView loginTextView;
    final HttpRequestFactory httpEngine = new HttpRequestFactory();
    private MainActivity mainActivity;

    public Login (TextView _loginTextView, TextView _userNameTextView, TextView _passwordTextView, MainActivity _mainActivity ) {
        this.loginTextView = _loginTextView;
        this.userNameTextView = _userNameTextView;
        this.passwordTextView = _passwordTextView;
        this.mainActivity = _mainActivity;
    }

    @Override
    public void tryLogin() {
        AsyncLogin request = new AsyncLogin(loginTextView, userNameTextView.getText().toString(), passwordTextView.getText().toString() );
        request.execute();
    }

    @Override
    public String whoAmI(String _token) {
        return null;
    }
}
