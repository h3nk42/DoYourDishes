package com.control.logic;

import android.widget.TextView;

import com.control.networkHttp.HttpRequestFactory;
import com.view.gui.HomeActivity;
import com.view.gui.MainActivity;

public class HomeController {

    private TextView whoAmItextView;
    final HttpRequestFactory httpEngine = new HttpRequestFactory();
    private HomeActivity homeActivity;
    private String token;


    public HomeController(TextView _whoAmItextView, String _token, HomeActivity _homeActivity) {
        this.whoAmItextView = _whoAmItextView;
        this.homeActivity = _homeActivity;
        this.token = _token;
    }

    public void whoAmI() {
        AsyncWhoAmI request = new AsyncWhoAmI(whoAmItextView, token);
        request.execute();
    }


}
