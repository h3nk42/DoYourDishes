package com.control.logic;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.view.gui.HomeActivity;
import com.view.gui.LoginActivity;
import com.view.gui.RegisterActivity;

public class RegisterController implements RegisterControllerInterface {

    private static final String TAG = "RegisterController";

    final TextInputEditText userNameEditText;
    final TextInputEditText passwordEditText;
    final TextInputEditText confirmPasswordEditText;
    final Button registerButton;
    private RegisterActivity registerActivity;
    public ControlState state;


    public RegisterController(Button _registerButton, TextInputEditText _userNameTextView, TextInputEditText _passwordTextView, TextInputEditText _confirmPasswordEditText, RegisterActivity _registerActivity ) {
        this.userNameEditText = _userNameTextView;
        this.passwordEditText = _passwordTextView;
        this.confirmPasswordEditText = _confirmPasswordEditText;
        this.registerActivity = _registerActivity;
        this.registerButton = _registerButton;
        this.state = ControlState.NOT_REGISTERED;
        Log.d(TAG, "Constructor: state == " + this.state);
    }

    public void registerUser(){
        registerButton.setEnabled(false);
        String password = passwordEditText.getText().toString();
        String cPassword = confirmPasswordEditText.getText().toString();
        String userName = userNameEditText.getText().toString();

        if ( password.equals(cPassword) ){
            AsyncTask request = new AsyncTask(userName, password, "REGISTER_USER", this);
            request.execute();
        } else {
            showToast("passwords don't match");
        }
    }

    public void showToast(String responseText ){
        registerButton.setEnabled(true);
        switch(responseText){
            case("INVALID_INPUT"):
                responseText = "please give proper input" ;
                break;
            case("USERNAME_TAKEN"):
                responseText = "userName taken.." ;
        }
        Toast toast = Toast.makeText(registerActivity, responseText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 20, 100);
        toast.show();
        this.state = ControlState.REGISTER_USER_ERROR;
        Log.d(TAG, "updateUi: state == " + this.state);
    }

    @Override
    public void startHomeView(String token) {
        this.state = ControlState.LOGGED_IN;
        Intent intent = new Intent(registerActivity, HomeActivity.class);
        intent.putExtra("TOKEN", token);
        registerActivity.startActivity(intent);
        Log.d(TAG, "startHomeView: state == " + this.state);
    }

}
