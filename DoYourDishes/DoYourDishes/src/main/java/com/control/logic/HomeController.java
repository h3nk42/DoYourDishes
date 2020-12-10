package com.control.logic;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.view.gui.HomeActivity;

public class HomeController implements HomeControllerInterface {

    private TextView whoAmItextView;
    private HomeActivity homeActivity;
    private String token;

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
