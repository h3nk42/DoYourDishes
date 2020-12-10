package com.control.logic;

import android.widget.TextView;

import com.control.networkHttp.HttpRequest;
import com.view.gui.HomeActivity;

public class HomeController implements HomeControllerInterface {

    private TextView whoAmItextView;
    final HttpRequest httpEngine = new HttpRequest();
    private HomeActivity homeActivity;
    private String token;
    ActiveState state;

    // TextView; String; Homeactivity
    public HomeController(TextView _whoAmItextView, String _token, HomeActivity _homeActivity) {
        this.whoAmItextView = _whoAmItextView;
        this.homeActivity = _homeActivity;
        this.token = _token;

    }

    public void whoAmI() {
        AsyncTask request = new AsyncTask(token, "WHO_AM_I", this);
        request.execute();
    }

    @Override
    public void updateUi(String responseText) {
        whoAmItextView.setText(responseText);
    }

}
