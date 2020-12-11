package com.control.logic;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.view.gui.HomeActivity;


/**
 * This class implements control functionality for the Home_Activity
 *
 * @value TextView holds an output field given in constructor by Home_Activity
 * @value homeActivity holds the controlled Activity given in constructor by Home_Activity
 * @value state holds the instance of state, mainly for testing purposes
 * @value TAG, purpose is using it on Log.d for debugging
 */

public class HomeController implements HomeControllerInterface {

    private static final String TAG = "HomeController";
    private final TextView whoAmItextView;
    private final HomeActivity homeActivity;
    private final String token;
    private ControlState state;


    public HomeController(TextView _whoAmItextView, String _token, HomeActivity _homeActivity) {
        this.whoAmItextView = _whoAmItextView;
        this.homeActivity = _homeActivity;
        this.token = _token;

        Toast toast = Toast.makeText(homeActivity, "you're logged in!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 20, 150);
        toast.show();
    }

    public void whoAmI() {
        AsyncTask request = new AsyncTask(token, "WHO_AM_I", this);
        request.execute();
    }

    @Override
    public void updateUi(String responseText) {
        whoAmItextView.setText("you are: " + responseText);
    }

}
