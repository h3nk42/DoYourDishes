package com.control.controllerLogic;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.control.asyncLogic.fetchPlan.FetchPlanUser;
import com.control.asyncLogic.fetchPlan.FetchPlanFacade;
import com.control.asyncLogic.fetchPlan.FetchPlanFacadeFactory;
import com.control.asyncLogic.login.LoginUser;
import com.control.asyncLogic.login.LoginFacade;
import com.control.asyncLogic.login.LoginFacadeFactory;
import com.view.gui.HomeActivity;
import com.view.gui.LoginActivity;

/**
 *  This class implements control functionality for the Login_Activity
 * @value userNameEditText holds the userName input field given in constructor by LoginActivity
 * @value passwordEditText holds the password input field given in constructor by LoginActivity
 * @value loginActivity holds the controlled Activity given in constructor by LoginActivity
 * @value loginButton holds the loginButton so it can be deactivated while active async request
 * @value state holds the instance of state, mainly for testing purposes
 * @value TAG, purpose is using it on Log.d for debugging
 */

public class LoginController implements LoginControllerInterface, LoginUser, FetchPlanUser {
    private static final String TAG = "LoginController";
    final EditText userNameEditText;
    final EditText passwordEditText;
    final Button loginButton;
    private LoginActivity loginActivity;
    public DebugState state;

    private String responseToken;
    private String responseUserName;
    private String responsePlanId;
    private String responsePlanName;
    private String responsePlanOwner;

    public LoginController(Button _loginButton, EditText _userNameTextView, EditText _passwordTextView, LoginActivity _mainActivity ) {
        this.userNameEditText = _userNameTextView;
        this.passwordEditText = _passwordTextView;
        this.loginActivity = _mainActivity;
        this.loginButton = _loginButton;
        this.state = DebugState.NOT_LOGGED_IN;
        Log.d(TAG, "Constructor: state == " + this.state);
    }

    /**
     *  This method creates an AsyncTask with METHOD: "LOG_IN", sending userName and password,
     *  also passing itself for callBack functionality when the request came back ( to update the ui )
     */
    @Override
    public void tryLogin() {
        LoginFacade loginFacade = LoginFacadeFactory.produceLoginFacade();
        loginFacade.loginCallAsync(
                userNameEditText.getText().toString(),
                passwordEditText.getText().toString(),
                this
        );
        loginButton.setEnabled(false);
    }

    /**
     *  this method shows a Toast on the View, also called from asyncTask to show requestErrors
     */
    @Override
    public void errorCallbackLogIn(String errorInfo) {
        loginButton.setEnabled(true);
        showToast(errorInfo);
    }


    public void showToast(String toastText) {
        switch(toastText){
            case("INVALID_INPUT"):
                //toastText = R.string.TOAST_STRING_NO_USERNAME_OR_PASSWORD_GIVEN;
                toastText = "no username/password given" ;
                break;
            case("WRONG_USER_OR_PW"):
                //toastText = String.valueOf(R.string.TOAST_STRING_WRONG_NAME_OR_PASSWORD_COMBO);
                toastText = "wrong name/password combo" ;
        }
        Toast toast = Toast.makeText(loginActivity, toastText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 20, 630);
        toast.show();
        this.state = DebugState.LOG_IN_ERROR;
    }

    @Override
    public void successCallbackLogin (String _token, String _resUserName, String _resUserPlanId) {
        this.responseToken = _token;
        this.responseUserName = _resUserName;
        this.responsePlanId = _resUserPlanId;
        if (_resUserPlanId.equals("null")) {
            startHomeActivity(
                    _token,
                    _resUserName,
                    _resUserPlanId,
                    "null",
                    "null"
            );
        }else{
            FetchPlanFacade fetchPlanFacade = FetchPlanFacadeFactory.produceFetchPlanFacade();
            fetchPlanFacade.fetchPlanCallAsync(_token, this);
        }
    }

    /**
     * This method is used as a callBack in AsyncTask to start the Home_Activity when login was successful
     * all the params are passed to the home_activity, so it immediateley knows who is logged in and if it should show the in_plan or not_in_plan layout
     * @param _planName the planName of the users plan ( == "null" if user is in no plan )
     * @param _planOwner the planOwner of the users plan ( == "null" if user is in no plan )
     */

    @Override
    public void successCallbackFetchPlan(String _planName, String _planOwner) {
        this.state = DebugState.LOGGED_IN;
        this.responsePlanName = _planName;
        this.responsePlanOwner = _planOwner;
        startHomeActivity(
                this.responseToken,
                this.responseUserName,
                this.responsePlanId,
                this.responsePlanName,
                this.responsePlanOwner
        );
        Log.d(TAG, "startHomeView: 1: " + this.responseToken + "2: " + this.responseUserName+ "3: " +this.responsePlanId + "4: " +  this.responsePlanName+ "5: " +  this.responsePlanOwner);
        Log.d(TAG, "startHomeView: state == " + this.state);
    }
    @Override
    public void errorCallbackFetchPlan(String errorInfo) {
        loginButton.setEnabled(true);
        showToast(errorInfo);
    }

    @Override
    public void startHomeActivity(String _token, String _resUserName, String _resUserPlanId, String _planName, String _planOwner) {
        this.state = DebugState.LOGGED_IN;
        Intent intent = new Intent(loginActivity, HomeActivity.class);
        intent.putExtra("TOKEN", _token);
        intent.putExtra("USERNAME", _resUserName);
        intent.putExtra("USERPLANID", _resUserPlanId);
        intent.putExtra("PLANNAME", _planName);
        intent.putExtra("PLANOWNER", _planOwner);
        loginActivity.startActivity(intent);
        Log.d(TAG, "startHomeView: state == " + this.state);
    }

    /**
     *  This method empties the Data in inputs, called when the user restarts this activity (from login_activity)
     */
    @Override
    public void resetData() {
        loginButton.setEnabled(true);
        userNameEditText.setText("");
        passwordEditText.setText("");
    }
}
