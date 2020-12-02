package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;

import com.control.logic.AsynchronousRequest;
import com.control.logic.Crud;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.view.R;


public class MainActivity extends AppCompatActivity {

    MainActivity thisClass = this;

    String test;

    Crud testCrud = new Crud();

    private static final String TAG="MainActivity";

    private void changeString() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.showFetchData);
        textView.setText("");

        final Button buttonAuth = (Button) findViewById(R.id.fetchDataButton);

        buttonAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent activityChangeIntent = new Intent(MainActivity.this, BluetoothActivity.class);
//                MainActivity.this.startActivity(activityChangeIntent);
                AsynchronousRequest request = new AsynchronousRequest(thisClass, textView);
                request.execute();
                 // textView.setText(testCrud.fetchData());
            }
        });

        Log.d(TAG, "onCreate: out");
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