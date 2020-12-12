package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.control.logic.LoginController;
import com.control.logic.RegisterController;
import com.google.android.material.textfield.TextInputEditText;
import com.view.R;

public class RegisterActivity extends AppCompatActivity {

    public static RegisterActivity registerActivity;
    public static Boolean registerWasOpen = false;

    private static final String TAG="LoginActivity";
    private TextInputEditText userNameEditText;
    private TextInputEditText passwordEditText;
    private RegisterController registerLogic;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerActivity = this;
        registerWasOpen = true;
        this.userNameEditText = (TextInputEditText) findViewById(R.id.registerUserNameTextField);
        this.passwordEditText = (TextInputEditText) findViewById(R.id.registerPasswordTextField);
        this.registerButton = findViewById(R.id.registerButton);
        this.registerLogic = new RegisterController(registerButton, userNameEditText, passwordEditText, registerActivity);
        Log.d(TAG, "onCreate: out");

    }



    public void registerUserClick(View view) {
        registerLogic.registerUser();
    }

}