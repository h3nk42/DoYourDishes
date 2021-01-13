package com.control.controllerLogic.LandingLogic;

import android.content.Intent;

import com.control.controllerLogic.DebugState;
import com.view.gui.LandingActivity;
import com.view.gui.LoginActivity;
import com.view.gui.RegisterActivity;


/**
 * controls the Landing_activity
 *
 * @value landingActivity holds the instance of LandingActivity
 * @value state holds state for debugging purposes
 */
public class LandingController implements LandingControllerInterface {

    final LandingActivity landingActivity;
    public DebugState state;

    public LandingController(LandingActivity _landingActivity) {
        this.landingActivity = _landingActivity;
        this.state = DebugState.IN_LANDING;

    }

    /**
     * starts new register_activity
     * gets called from landing_activity by button click
     */
    @Override
    public void goToRegisterUser() {
        Intent intent = new Intent(landingActivity, RegisterActivity.class);
        landingActivity.startActivity(intent);
        this.state = DebugState.TO_REGISTER;
    }

    /**
     * starts new login_activity
     * gets called from landing_activity by button click
     */
    @Override
    public void goToLogin() {
        Intent intent = new Intent(landingActivity, LoginActivity.class);
        landingActivity.startActivity(intent);
        this.state = DebugState.TO_LOGIN;
    }
}
