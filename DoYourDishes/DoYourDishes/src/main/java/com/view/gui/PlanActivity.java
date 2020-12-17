package com.view.gui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.control.controllerLogic.PlanLogic.PlanController;
import com.control.controllerLogic.PlanLogic.fragmentControllers.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.view.R;
import com.view.gui.fragments.ScoreFragment;
import com.view.gui.fragments.TasksFragment;
import com.view.gui.fragments.UsersFragment;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {


    private PlanController planController;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TasksFragment tasksFragment;
    private UsersFragment usersFragment;
    private ScoreFragment scoreFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_plan);

        this.tabLayout = findViewById(R.id.tabLayoutNavigation);
        this.viewPager = findViewById(R.id.planContent);
        this.tasksFragment = new TasksFragment();
        this.usersFragment = new UsersFragment();
        this.scoreFragment = new ScoreFragment();
        tabLayout.setupWithViewPager(viewPager);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPager.setAdapter(viewPagerAdapter);

        viewPagerAdapter.addFragment(tasksFragment, "tasks");
        viewPagerAdapter.addFragment(usersFragment, "users");
        viewPagerAdapter.addFragment(scoreFragment, "score");


        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#695d47"));

        Intent intent = getIntent();

        planController = new PlanController(
                intent.getStringExtra("TOKEN"),
                intent.getStringExtra("PLANNAME"),
                intent.getStringExtra("USERNAME"),
                intent.getStringExtra("USERPLANID"),
                intent.getStringExtra("PLANOWNER"),
                tasksFragment,
                usersFragment,
                scoreFragment,
                this);
    }

    public void openUsers(View view) {
        planController.openUsers();
    }

    public PlanController getPlanController(){
        return this.planController;
    }
}
