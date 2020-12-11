package com.control.logic;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.view.gui.HomeActivity;
import com.view.gui.LoginActivity;

/**
 *  This class implements control functionality for the Login_Activity
 * @value userNameEditText holds the userName input field given in constructor by LoginActivity
 * @value passwordEditText holds the password input field given in constructor by LoginActivity
 * @value TextView holds an output field given in constructor by LoginActivity
 * @value loginActivity holds the controlled Activity given in constructor by LoginActivity
 * @value state holds the instance of state, mainly for testing purposes
 * @value TAG, purpose is using it on Log.d for debugging
 */

public class LoginController implements LoginControllerInterface{
    private static final String TAG = "LoginController";

    final EditText userNameEditText;
    final EditText passwordEditText;
    final TextView showLoginDataTextView;
    final Button loginButton;
    private LoginActivity loginActivity;
    public ControlState state;


    public LoginController(Button _loginButton, TextView _loginTextView, EditText _userNameTextView, EditText _passwordTextView, LoginActivity _mainActivity ) {
        this.showLoginDataTextView = _loginTextView;
        this.userNameEditText = _userNameTextView;
        this.passwordEditText = _passwordTextView;
        this.loginActivity = _mainActivity;
        this.loginButton = _loginButton;
        this.state = ControlState.NOT_LOGGED_IN;
        Log.d(TAG, "Constructor: state == " + this.state);
    }

    /**
     *  This method creates an AsyncTask with METHOD: "LOG_IN", sending userName and password,
     *  also passing itself for callBack functionality when the request came back ( to update the ui )
     */

    @Override
    public void tryLogin() {
        loginButton.setEnabled(false);
        AsyncTask request = new AsyncTask(userNameEditText.getText().toString(), passwordEditText.getText().toString(), "LOG_IN", this);
        request.execute();
    }

    /**
     *  This method is used as a callBack in AsyncTask to show a Toast with information on UI
     */

    @Override
    public void showToast(String responseText) {
        //showLoginDataTextView.setText(responseText);
        loginButton.setEnabled(true);
        switch(responseText){
            case("INVALID_INPUT"):
                responseText = "no username/password given" ;
                break;
            case("WRONG_USER_OR_PW"):
                responseText = "wrong name/password combo" ;
        }

        Toast toast = Toast.makeText(loginActivity, responseText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 20, 100);
        toast.show();
        this.state = ControlState.LOG_IN_ERROR;
        Log.d(TAG, "updateUi: state == " + this.state);
    }

    /**
     *  This method is used as a callBack in AsyncTask to start the HOME_VIEW when login was succesfull
     */
    @Override
    public void startHomeView(String token) {
        this.state = ControlState.LOGGED_IN;
        Intent intent = new Intent(loginActivity, HomeActivity.class);
        intent.putExtra("TOKEN", token);
        loginActivity.startActivity(intent);
        Log.d(TAG, "startHomeView: state == " + this.state);
    }

    /**
     *  This method empties the Data in inputs, called when the user restarts this activity ( going back with the arrow, from HomeView )
     */
    @Override
    public void resetData() {
        loginButton.setEnabled(true);
        showLoginDataTextView.setText("");
        userNameEditText.setText("");
        passwordEditText.setText("");
    }

}
