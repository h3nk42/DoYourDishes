package com.control.controllerLogic.PlanLogic;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.control.asyncLogic.addTaskToPlan.AddTaskUser;
import com.control.asyncLogic.addUserToPlan.AddUserUser;
import com.control.asyncLogic.deleteTask.DeleteTaskUser;
import com.control.asyncLogic.fetchPlan.FetchPlanFacade;
import com.control.asyncLogic.fetchPlan.FetchPlanFacadeFactory;
import com.control.asyncLogic.fetchPlan.FetchPlanUser;
import com.control.asyncLogic.fulfillTask.FulfillTaskUser;
import com.control.asyncLogic.removeUserFromPlan.RemoveUserUser;
import com.control.controllerLogic.DebugState;
import com.model.dataModel.Task;
import com.model.dataModel.User;

import com.view.R;
import com.view.gui.PlanActivity;
import com.view.gui.fragments.ScoreFragment;
import com.view.gui.fragments.TasksFragment;
import com.view.gui.fragments.UsersFragment;

import java.util.List;

public class PlanController implements PlanControllerInterface, FetchPlanUser, AddUserUser, RemoveUserUser, AddTaskUser, DeleteTaskUser, FulfillTaskUser {

    private static final String TAG = "PlanController";
    private static String token;
    public static String userPlanName;
    private final String userName;
    private final String userPlanId;
    private final String userPlanOwner;
    private final User activeUser;

    public List<User> users;
    private List<Task> tasks;

    private UsersFragment usersFragment;
    private TasksFragment tasksFragment;
    private ScoreFragment scoreFragment;
    private TextView planNameTopBarTextView;
    PlanActivity planActivity;
    FetchPlanUser fetchPlanUser = this;
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


        final SwipeRefreshLayout pullToRefresh = planActivity.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPlanFacade.fetchPlanCallAsync(token, fetchPlanUser);
                pullToRefresh.setRefreshing(false);
            }
        });

    }


    @Override
    public void successCallbackFetchPlan(String _planName, String _planId, String _planOwner, List<User> users, List<Task> tasks) {
        this.users = users;
        this.tasks = tasks;
        this.usersFragment.renderData(users);
        this.tasksFragment.renderData(tasks);
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

    @Override
    public void showToast(String responseText) {
        switch(responseText){
            case("INVALID_INPUT"):
                responseText = "invalid input" ;
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

    @Override
    public String getToken(){
        return token;
    }

    @Override
    public void successCallbackRemoveUser(String __successMessage, Boolean _deleteHimself) {
        if(_deleteHimself){
            finishPlanActivity();
        } else {
            fetchPlanFacade.fetchPlanCallAsync(token, this);
        }
    }

    @Override
    public void errorCallbackRemoveUser(String errorInfo) {
        showToast(errorInfo);
    }

    @Override
    public void successCallbackAddTask(String __successMessage) {
        fetchPlanFacade.fetchPlanCallAsync(token, this);
    }

    @Override
    public void errorCallbackAddTask(String errorInfo) {
        showToast(errorInfo);
    }

    @Override
    public void successCallbackDeleteTask(String responseText) {
        fetchPlanFacade.fetchPlanCallAsync(token, this);
    }

    @Override
    public void errorCallbackDeleteTask(String errorInfo) {
        showToast(errorInfo);

    }

    @Override
    public void successCallbackFulfillTask(String responseText) {
        fetchPlanFacade.fetchPlanCallAsync(token, this);
    }

    @Override
    public void errorCallbackFulfillTask(String errorInfo) {
        showToast(errorInfo);
    }

    @Override
    public void fetchData(){
        fetchPlanFacade.fetchPlanCallAsync(token, this);
    }

    @Override
    public void finishPlanActivity(){
        planActivity.finish();
        return;
    }

    @Override
    public String getActiveUserName(){
        return this.userName;
    }
}
