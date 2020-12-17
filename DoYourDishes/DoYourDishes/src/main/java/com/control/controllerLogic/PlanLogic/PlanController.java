package com.control.controllerLogic.PlanLogic;

import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.control.asyncLogic.fetchPlan.FetchPlanFacade;
import com.control.asyncLogic.fetchPlan.FetchPlanFacadeFactory;
import com.control.asyncLogic.fetchPlan.FetchPlanUser;
import com.model.dataModel.User;

import com.view.R;
import com.view.gui.PlanActivity;
import com.view.gui.fragments.ScoreFragment;
import com.view.gui.fragments.TasksFragment;
import com.view.gui.fragments.UsersFragment;

import java.util.ArrayList;
import java.util.List;

public class PlanController implements FetchPlanUser {

    public static String readmyString = "user array";
    private final String token;
    public static String userPlanName;
    private final String userName;
    private final String userPlanId;
    private final String userPlanOwner;
    private final User activeUser;

    public List<User> users;
    public ArrayList<String> usersArrayList;

    private UsersFragment usersFragment;
    private TasksFragment tasksFragment;
    private ScoreFragment scoreFragment;
    private TextView planNameTopBarTextView;
    PlanActivity planActivity;



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

        FetchPlanFacade fetchPlanFacade = FetchPlanFacadeFactory.produceFetchPlanFacade();
        fetchPlanFacade.fetchPlanCallAsync(_token, this);

    }


    @Override
    public void successCallbackFetchPlan(String _planName, String _planOwner, List<User> users) {
        this.users = users;

        this.usersFragment.renderData();
    }

    @Override
    public void errorCallbackFetchPlan(String errorInfo) {

    }
}
