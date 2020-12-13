package com.control.logic;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.model.dataModel.User;
import com.view.R;
import com.view.gui.HomeActivity;
import com.view.gui.LandingActivity;
import com.view.gui.LoginActivity;
import com.view.gui.RegisterActivity;


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

    private TextView welcomeUserTextView;

    private User activeUser;
    private ControlState state;


    public HomeController(TextView _whoAmItextView, String _token, HomeActivity _homeActivity) {
        this.whoAmItextView = _whoAmItextView;
        this.homeActivity = _homeActivity;
        this.token = _token;

        //whoAmI();

        Toast toast = Toast.makeText(homeActivity, "you're logged in!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 20, 150);
        toast.show();
    }

    @Override
    public void whoAmI() {
        AsyncTask request = new AsyncTask(token, "WHO_AM_I", this);
        request.execute();
    }

    @Override
    public void finishPrevActivities(){
        LandingActivity.landingActivity.finish();
        if(LoginActivity.loginWasOpened) {
            LoginActivity.loginActivity.finish();
        }
        if( RegisterActivity.registerWasOpen){
            RegisterActivity.registerActivity.finish();
        }
    }

    public void updateUser(String _responseUserName, String _responseUserPlan){
        if (this.activeUser != null) {
        }else {
            this.activeUser = new User(_responseUserName,_responseUserPlan);
        }
        if(activeUser.getPlan().equals("null")) {
            changeLayout("NO_PLAN");
        } else {
            changeLayout("IN_PLAN");
        }
        whoAmItextView.setText("your plan: " + this.activeUser.getPlan() + "your name: " + activeUser.getUserName());
    }

    private void changeLayout(String whichLayout){
        switch(whichLayout){
            case("NO_PLAN"):
                homeActivity.setContentView(R.layout.activity_home_no_plan);
                welcomeUserTextView = (TextView) homeActivity.findViewById(R.id.welcomeUserNameTextView);
                welcomeUserTextView.setText("Welcome " + activeUser.getUserName() + "!");
                break;
            case("IN_PLAN"):
                break;
        }

    }

    @Override
    public void updateUi(String responseText) {
        whoAmItextView.setText("you are: " + responseText);
    }

}
