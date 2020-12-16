package com.control.controllerLogic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.control.asyncLogic.createPlan.CreatePlanFacade;
import com.control.asyncLogic.createPlan.CreatePlanFacadeFactory;
import com.control.asyncLogic.createPlan.CreatePlanUser;
import com.control.asyncLogic.deletePlan.DeletePlanFacade;
import com.control.asyncLogic.deletePlan.DeletePlanFacadeFactory;
import com.control.asyncLogic.deletePlan.DeletePlanUser;
import com.model.dataModel.Plan;
import com.model.dataModel.User;
import com.view.R;
import com.view.gui.HomeActivity;
import com.view.gui.LandingActivity;
import com.view.gui.LoginActivity;
import com.view.gui.PlanActivity;
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

public class HomeController implements HomeControllerInterface, CreatePlanUser, DeletePlanUser {

    private static final String TAG = "HomeController";
    private final HomeActivity homeActivity;
    private CreatePlanUser createPlanUser = this;
    private DeletePlanUser deletePlanUser = this;
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

    public void deletePlan(){
        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
        builder.setTitle("really delete plan?");
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeletePlanFacade deletePlanFacade = DeletePlanFacadeFactory.produceDeletePlanFacade();
                deletePlanFacade.deletePlanCallAsync(token, deletePlanUser);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
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
                CreatePlanFacade createPlanFacade = CreatePlanFacadeFactory.produceCreatePlanFacade();
                createPlanFacade.createPlanCallAsync(token, input.getText().toString(), createPlanUser);
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

    public void showToast(String responseText) {
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


    @Override
    public void successCallbackCreatePlan(String _planOwner,String _planName, String _planId) {
        List<String> planUsers = new ArrayList<String>();
        planUsers.add(activeUser.getUserName());
        this.plan = new Plan(_planOwner, _planName, _planId, planUsers);
        changeLayout("IN_PLAN");
    }

    @Override
    public void errorCallbackCreatePlan(String errorInfo) {
        showToast(errorInfo);
    }

    @Override
    public void successCallbackDeletePlan(String responseText) {
        activeUser.setPlan("null");
        renderLayout();
        showToast(responseText);
    }

    @Override
    public void errorCallbackDeletePlan(String errorInfo) {
        showToast(errorInfo);
    }

    public void openPlanActivity(){
        Intent intent = new Intent(homeActivity, PlanActivity.class);
        homeActivity.startActivity(intent);
    }

}
