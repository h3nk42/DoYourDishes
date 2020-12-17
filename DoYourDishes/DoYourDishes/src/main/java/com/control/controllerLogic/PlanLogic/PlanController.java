package com.control.controllerLogic.PlanLogic;

import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.control.asyncLogic.addUserToPlan.AddUserUser;
import com.control.asyncLogic.fetchPlan.FetchPlanFacade;
import com.control.asyncLogic.fetchPlan.FetchPlanFacadeFactory;
import com.control.asyncLogic.fetchPlan.FetchPlanUser;
import com.control.controllerLogic.DebugState;
import com.model.dataModel.User;

import com.view.R;
import com.view.gui.PlanActivity;
import com.view.gui.fragments.ScoreFragment;
import com.view.gui.fragments.TasksFragment;
import com.view.gui.fragments.UsersFragment;

import java.util.ArrayList;
import java.util.List;

public class PlanController implements FetchPlanUser, AddUserUser {

    private static String token;
    public static String userPlanName;
    private final String userName;
    private final String userPlanId;
    private final String userPlanOwner;
    private final User activeUser;

    public List<User> users;

    private UsersFragment usersFragment;
    private TasksFragment tasksFragment;
    private ScoreFragment scoreFragment;
    private TextView planNameTopBarTextView;
    PlanActivity planActivity;
    private FetchPlanFacade fetchPlanFacade;



    public PlanController(String _token,
                          String _planName,
                          String _userName,
                          String _userPlanId,
                          String _userPlanOwner,
                          TasksFragment tasksFragment,
                          UsersFragment usersFragment,
                          ScoreFragment scoreFragment,
                          PlanActivity planActivity
    ){
        this.tasksFragment = tasksFragment;
        this.usersFragment = usersFragment;
        this.scoreFragment = scoreFragment;
        this.planActivity = planActivity;
        this.token = _token;
        this.userPlanName = _planName;
        this.userName = _userName;
        this.userPlanId = _userPlanId;
        this.userPlanOwner = _userPlanOwner;
        this.activeUser = new User(userName,userPlanId, 0);

        this.planNameTopBarTextView = (TextView) planActivity.findViewById(R.id.planNameTopBarTextView);
        planNameTopBarTextView.setText(" " +_planName);

        fetchPlanFacade = FetchPlanFacadeFactory.produceFetchPlanFacade();
        fetchPlanFacade.fetchPlanCallAsync(token, this);

    }


    @Override
    public void successCallbackFetchPlan(String _planName, String _planOwner, List<User> users) {
        this.users = users;

        this.usersFragment.renderData(users);
    }

    @Override
    public void errorCallbackFetchPlan(String errorInfo) {

    }

    @Override
    public void successCallbackAddUser(String __successMessage) {
        fetchPlanFacade.fetchPlanCallAsync(token, this);
    }

    @Override
    public void errorCallbackAddUser(String errorInfo) {
        showToast(errorInfo);
    }

    public void showToast(String responseText) {
        switch(responseText){
            case("INVALID_INPUT"):
                responseText = "no username given" ;
                break;
            case("WRONG_USER_OR_PW"):
                responseText = "wrong name/password combo" ;
            case("USER_DOES_NOT_EXIST"):
                responseText = "no user found with that name" ;
        }
        Toast toast = Toast.makeText(planActivity, responseText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 20, 200);
        toast.show();
    }

    public String getToken(){
        return token;
    }


}
