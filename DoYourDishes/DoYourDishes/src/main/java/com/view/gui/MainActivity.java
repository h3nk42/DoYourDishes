package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.control.logic.Login;
import com.view.R;


public class MainActivity extends AppCompatActivity {
    MainActivity thisActivity = this;
    private static final String TAG="MainActivity";
    private TextView loginTextView;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private Login loginLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginTextView = (TextView) findViewById(R.id.showLoginDataTextView);
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Intent activityChangeIntent = new Intent(MainActivity.this, BluetoothActivity.class);
        //                MainActivity.this.startActivity(activityChangeIntent);
        Log.d(TAG, "onCreate: out");
    }

    public void onClicker(View view) {
        loginTextView = (TextView) findViewById(R.id.showLoginDataTextView);
        userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        loginLogic = new Login(loginTextView, userNameTextView, passwordTextView, thisActivity);
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
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

}