package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.control.logic.LandingController;
import com.view.R;

/**
 * First activity of the application
 * @value landingController holds the control for this activity (view)
 * @value landingActivity holds the instance of this activity to pass it to the control
 */
public class LandingActivity extends AppCompatActivity {

    private LandingController landingController;
    public static LandingActivity landingActivity;

    /**
     * creates a new landingController, passing the activity itself to its constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        landingActivity = this;
        landingController = new LandingController(this);
    }

    /**
     * gets called by the to registration button
     * -> calls the control function
     */
    public void toRegisterUserClick(View view){
        landingController.goToRegisterUser();
    }

    /**
     * gets called by the to login button
     * -> calls the control function
     */
    public void toLoginClick(View view){
        landingController.goToLogin();
    }

}