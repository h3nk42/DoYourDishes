package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.control.controllerLogic.RegisterController;
import com.google.android.material.textfield.TextInputEditText;
import com.view.R;

/**
 *  this is the activity that handles user logIn
 * @value registerActivity holds itself to pass to control and so the activity can get finished from another activity
 * @value registerWasOpen gets set to true if a single instance of this activity was existent,
 * so the Homeactivity knows which activities to .finish() when started
 * @value TAG for debugger logging
 * @value registerController holds instance of control class
 */

public class RegisterActivity extends AppCompatActivity {

    public static RegisterActivity registerActivity;
    public static Boolean registerWasOpen = false;
    private static final String TAG="LoginActivity";
    private RegisterController registerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerActivity = this;
        registerWasOpen = true;

        this.registerController = new RegisterController(
                findViewById(R.id.registerButton),
                (TextInputEditText) findViewById(R.id.registerUserNameTextField),
                (TextInputEditText) findViewById(R.id.passwordTextField),
                (TextInputEditText) findViewById(R.id.confirmRegisterPasswordEditText),
                this);
        Log.d(TAG, "onCreate: out");
    }

    /**
     * gets called by registerButton
     * @param view
     */
    public void registerUserClick(View view) {
        registerController.registerUser();
    }
}