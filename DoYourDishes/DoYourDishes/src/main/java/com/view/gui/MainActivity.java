package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.control.logic.LoginController;
import com.view.R;


public class MainActivity extends AppCompatActivity {
    MainActivity thisActivity = this;
    private static final String TAG="MainActivity";
    private TextView showLoginDataTextView;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private LoginController loginLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Intent activityChangeIntent = new Intent(MainActivity.this, BluetoothActivity.class);
        //                MainActivity.this.startActivity(activityChangeIntent);

        this.showLoginDataTextView = (TextView) findViewById(R.id.showLoginDataTextView);
        this.userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        this.passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        this.loginLogic = new LoginController(showLoginDataTextView, userNameTextView, passwordTextView, thisActivity);
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