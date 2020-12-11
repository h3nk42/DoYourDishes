package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.control.logic.LoginController;
import com.view.R;


public class LoginActivity extends AppCompatActivity {
    public static LoginActivity loginActivity;
    private static final String TAG="LoginActivity";
    private TextView showLoginDataTextView;
    private EditText userNameTextView;
    private EditText passwordTextView;
    private LoginController loginLogic;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
      //  Intent activityChangeIntent = new Intent(MainActivity.this, BluetoothActivity.class);
        //                MainActivity.this.startActivity(activityChangeIntent);
        loginActivity = this;
        this.showLoginDataTextView = (TextView) findViewById(R.id.showLoginDataTextView);
        this.userNameTextView = (EditText) findViewById(R.id.userNameEditTextView);
        this.passwordTextView = (EditText) findViewById(R.id.passwordEditTextView);
        this.loginButton = findViewById(R.id.toLoginButton);
        this.loginLogic = new LoginController(loginButton, showLoginDataTextView, userNameTextView, passwordTextView, loginActivity);
        Log.d(TAG, "onCreate: out");
    }

    public void login(View view) {
        loginLogic.tryLogin();
    }


    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: in");
        super.onStop();
        Log.d(TAG, "onStop: out");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: in");
        super.onDestroy();
        Log.d(TAG, "onDestroy: out");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");
        loginLogic.resetData();
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

}