package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


import com.control.logic.HomeController;
import com.view.R;


public class HomeActivity extends AppCompatActivity {

    private HomeController homeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        homeController = new HomeController(
                intent.getStringExtra("TOKEN"),
                intent.getStringExtra("PLANNAME"),
                intent.getStringExtra("USERNAME"),
                intent.getStringExtra("USERPLANID"),
                intent.getStringExtra("PLANOWNER"),
                this);
        homeController.finishPrevActivities();

    }

    public void createPlan(View view){
        homeController.createPlanDialog();
    }

}