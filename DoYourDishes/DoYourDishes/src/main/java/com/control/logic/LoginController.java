package com.control.logic;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.view.gui.HomeActivity;
import com.view.gui.MainActivity;

public class LoginController implements LoginControllerInterface{
    private static final String TAG = "LoginController";


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
        Log.d(TAG, "Constructor: state == " + this.state);
    }

    @Override
    public void tryLogin() {
        AsyncTask request = new AsyncTask(userNameTextView.getText().toString(), passwordTextView.getText().toString(), "LOG_IN", this);
        request.execute();
    }

    @Override
    public void updateUi(String responseText) {
        //showLoginDataTextView.setText(responseText);

        switch(responseText){
            case("INVALID_INPUT"):
                responseText = "no username/password given" ;
                break;
            case("WRONG_USER_OR_PW"):
                responseText = "wrong name/password combo" ;
        }

        Toast toast = Toast.makeText(mainActivity, responseText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 20, 70);
        toast.show();
        this.state = ControllState.LOG_IN_ERROR;
        Log.d(TAG, "updateUi: state == " + this.state);
    }
    @Override
    public void startHomeView(String token) {
        this.state = ControllState.LOGGED_IN;
        Intent intent = new Intent(mainActivity, HomeActivity.class);
        intent.putExtra("TOKEN", token);
        mainActivity.startActivity(intent);
        Log.d(TAG, "startHomeView: state == " + this.state);
    }
    @Override
    public void resetData() {
        showLoginDataTextView.setText("");
        userNameTextView.setText("");
        passwordTextView.setText("");
    }



}
