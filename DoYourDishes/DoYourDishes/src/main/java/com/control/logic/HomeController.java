package com.control.logic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.model.dataModel.Plan;
import com.model.dataModel.User;
import com.view.R;
import com.view.gui.HomeActivity;
import com.view.gui.LandingActivity;
import com.view.gui.LoginActivity;
import com.view.gui.RegisterActivity;

import java.util.ArrayList;
import java.util.List;


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
    private final HomeActivity homeActivity;
    private final String token;
    private final HomeController homeController = this;

    private TextView welcomeUserTextView;

    private TextView planNameTextView;

    private User activeUser;
    private Plan plan;
    private DebugState state;
    private String planName;
    private String userName;
    private String userPlanId;
    private String userPlanOwner;


    public HomeController(String _token, String _planName, String _userName, String _userPlanId, String _userPlanOwner, HomeActivity _homeActivity) {

        this.homeActivity = _homeActivity;
        this.token = _token;
        this.planName = _planName;
        this.userName = _userName;
        this.userPlanId = _userPlanId;
        this.userPlanOwner = _userPlanOwner;

        this.activeUser = new User(userName,userPlanId);
        showToast("you're logged in!");
        renderLayout();

    }


    public void renderLayout() {
        if(activeUser.getPlan().equals("null")) {
            changeLayout("NO_PLAN");
        } else {
            // TODO wenn mehrere user im plan muessen diese zum Plan hinzugefuegt werden wie gebe ich PLAN an naechste activity weiter?
            // TODO was ist mit tasks ?? => tasks erst in plan activity fetchen
            List<String> planUsers = new ArrayList<String>();
            planUsers.add(activeUser.getUserName());
            this.plan = new Plan(userPlanOwner, planName,userPlanId,planUsers);
            changeLayout("IN_PLAN");
        }
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


    private void changeLayout(String whichLayout){
        switch(whichLayout){
            case("NO_PLAN"):
                homeActivity.setContentView(R.layout.activity_home_no_plan);
                welcomeUserTextView = (TextView) homeActivity.findViewById(R.id.welcomeUserNameTextView);
                welcomeUserTextView.setText("Welcome " + activeUser.getUserName() + "!");
                break;
            case("IN_PLAN"):
                homeActivity.setContentView(R.layout.activity_home_in_plan);
                planNameTextView = (TextView) homeActivity.findViewById(R.id.planNameTextView);
                welcomeUserTextView = (TextView) homeActivity.findViewById(R.id.welcomeUserNameTextView);
                welcomeUserTextView.setText("Welcome " + activeUser.getUserName() + "!");
                this.planNameTextView.setText(plan.getName());
                break;
        }
    }

    public void createPlanDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
        builder.setTitle("Name your plan!");

        final EditText input = new EditText(homeActivity);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("plan name");
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AsyncTask request = new AsyncTask(token, "CREATE_PLAN", input.getText().toString(), homeController);
                request.execute();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    public void callBackCreatedPlan(String _ownerName, String _planName, String _planId){
        List<String> planUsers = new ArrayList<String>();
        planUsers.add(activeUser.getUserName());
        this.plan = new Plan(_ownerName, _planName, _planId, planUsers);
        changeLayout("IN_PLAN");
    }

    public void showToast(String responseText) {
        //showLoginDataTextView.setText(responseText);
        switch(responseText){
            case("INVALID_INPUT"):
                responseText = "Plan name too long! maxlen 15" ;
                break;
            case("WRONG_USER_OR_PW"):
                responseText = "wrong name/password combo" ;
        }

        Toast toast = Toast.makeText(homeActivity, responseText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 20, 200);
        toast.show();
        this.state = DebugState.LOG_IN_ERROR;
        Log.d(TAG, "updateUi: state == " + this.state);
    }


}
