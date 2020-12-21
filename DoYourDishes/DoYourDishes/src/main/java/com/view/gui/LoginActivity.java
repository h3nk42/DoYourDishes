package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.control.controllerLogic.LoginLogic.LoginController;
import com.view.R;

/**
 *  this is the activity that handles user logIn
 * @value loginActivity holds itself to pass to control and so the activity can get finsihed from another activity
 * @value loginWasOpened gets set to true if a single instance of this activity was existent,
 * so the Homeactivity knows which activities to .finish() when started
 * @value TAG for debugger logging
 * @value loginController holds instance of control class
 */

public class LoginActivity extends AppCompatActivity implements LoginActivityInterface {

    public static LoginActivity loginActivity;
    public static Boolean loginWasOpened = false;
    private static final String TAG="LoginActivity";
    private LoginController loginController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginActivity = this;
        loginWasOpened = true;

        this.loginController = new LoginController(
                findViewById(R.id.toLoginButton),
                (EditText) findViewById(R.id.userNameEditTextView),
                (EditText) findViewById(R.id.passwordEditTextView),
                this);
        Log.d(TAG, "onCreate: out");
    }

    /**
     * gets called by loginButton
     * @param view
     */
    @Override
    public void login(View view) {
        loginController.tryLogin();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");
        loginController.resetData();
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

}