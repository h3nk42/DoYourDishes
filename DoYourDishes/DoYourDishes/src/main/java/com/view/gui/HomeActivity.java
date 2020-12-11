package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.control.logic.HomeController;
import com.view.R;


public class HomeActivity extends AppCompatActivity {

    private TextView whoAmITextView;
    private HomeController homeController;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);



        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN");
        whoAmITextView = (TextView) findViewById(R.id.whoAmITextView);
        whoAmITextView.setText("your JWToken: \n" + token);
        homeController = new HomeController(whoAmITextView, token, this);
        homeController.finishPrevActivities();

    }

    public void whoAmI(View view) {
       homeController.whoAmI();
    }
}