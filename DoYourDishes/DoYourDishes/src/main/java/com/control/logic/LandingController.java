package com.control.logic;

import android.content.Intent;

import com.view.gui.LandingActivity;
import com.view.gui.LoginActivity;
import com.view.gui.RegisterActivity;

public class LandingController implements LandingControllerInterface {

    final LandingActivity landingActivity;

    public LandingController(LandingActivity _landingActivity) {
        this.landingActivity = _landingActivity;
    }


    @Override
    public void goToRegisterUser() {
        Intent intent = new Intent(landingActivity, RegisterActivity.class);
        landingActivity.startActivity(intent);
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(landingActivity, LoginActivity.class);
        landingActivity.startActivity(intent);
    }

}
