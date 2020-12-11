package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.control.logic.LandingController;
import com.view.R;

public class LandingActivity extends AppCompatActivity {

    private LandingController landingController;
    public static LandingActivity landingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        landingActivity = this;
        landingController = new LandingController(this);
    }


    public void toRegisterUserClick(View view){
        landingController.goToRegisterUser();

    }

    public void toLoginClick(View view){
        landingController.goToLogin();

    }


}