package com.view.gui;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.control.controllerLogic.PlanController;
import com.google.android.material.tabs.TabLayout;
import com.view.R;

public class PlanActivity extends AppCompatActivity {


    private PlanController planController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_plan_tasks);

        TabLayout tabLayout = findViewById(R.id.tabLayoutNavigation);


        planController = new PlanController(this);
    }

    public void openUsers(View view) {
        planController.openUsers();
    }
}
