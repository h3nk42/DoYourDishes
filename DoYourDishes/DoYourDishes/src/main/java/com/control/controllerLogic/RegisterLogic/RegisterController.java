package com.control.controllerLogic.RegisterLogic;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;

import android.widget.Toast;

import com.control.asyncLogic.registerUser.RegisterUserFacade;
import com.control.asyncLogic.registerUser.RegisterUserFacadeFactory;
import com.control.asyncLogic.registerUser.RegisterUserUser;
import com.control.controllerLogic.DebugState;
import com.google.android.material.textfield.TextInputEditText;
import com.view.gui.HomeActivity;
import com.view.gui.RegisterActivity;

/**
 *  This class implements control functionality for the Register_Activity
 * @value userNameEditText holds the userName input field given in constructor by RegisterActivity
 * @value passwordEditText holds the password input field given in constructor by RegisterActivity
 * @value registerActivity holds the controlled Activity given in constructor by RegisterActivity
 * @value registerButton holds the registerButton so it can be deactivated while active async request
 * @value state holds the instance of state, mainly for testing purposes
 * @value TAG, purpose is using it on Log.d for debugging
 */

public class RegisterController implements RegisterControllerInterface, RegisterUserUser {

    private static final String TAG = "RegisterController";

    final TextInputEditText userNameEditText;
    final TextInputEditText passwordEditText;
    final TextInputEditText confirmPasswordEditText;
    final Button registerButton;
    private RegisterActivity registerActivity;
    public DebugState state;
    private String responseToken;
    private String responseUserName;
    private String responsePlanId;

    public RegisterController(Button _registerButton, TextInputEditText _userNameTextView, TextInputEditText _passwordTextView, TextInputEditText _confirmPasswordEditText, RegisterActivity _registerActivity) {
        this.userNameEditText = _userNameTextView;
        this.passwordEditText = _passwordTextView;
        this.confirmPasswordEditText = _confirmPasswordEditText;
        this.registerActivity = _registerActivity;
        this.registerButton = _registerButton;
        this.state = DebugState.NOT_REGISTERED;
        Log.d(TAG, "Constructor: state == " + this.state);
    }

    /**
     *  This method creates an AsyncTask with METHOD: "REGISTER_USER", sending userName and password,
     *  also passing itself for callBack functionality when the request came back ( to update the ui )
     *  also checks if confirmPassword and passwords match before sending request
     */
    @Override
    public void registerUser(){
        registerButton.setEnabled(false);
        String password = passwordEditText.getText().toString();
        String cPassword = confirmPasswordEditText.getText().toString();
        String userName = userNameEditText.getText().toString();

        if (!(password.equals(cPassword)) ) {
            showToast("passwords don't match");
            this.state = DebugState.REGISTER_USER_ERROR;
        }
        else if(password.length()<5) {
            showToast("password too short");
            this.state = DebugState.REGISTER_USER_ERROR;
        }
        else if(userName.length()<4) {
            showToast("username too short");
            this.state = DebugState.REGISTER_USER_ERROR;
        }
        else {
            RegisterUserFacade registerUserFacade = RegisterUserFacadeFactory.produceRegisterUserFacade();
            registerUserFacade.registerUserCallAsync(userName, password, this);
        }
    }

    /**
     *  this method shows a Toast on the View, also called from asyncTask to show requestErrors
     */
    @Override
    public void showToast(String toastText){
        registerButton.setEnabled(true);
        switch(toastText){
            case("INVALID_INPUT"):
                toastText = "please give proper input" ;
                break;
            case("USERNAME_TAKEN"):
                toastText = "userName taken.." ;
        }
        Toast toast = Toast.makeText(registerActivity, toastText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 20, 150);
        toast.show();
        this.state = DebugState.REGISTER_USER_ERROR;
        Log.d(TAG, "updateUi: state == " + this.state);
    }


    /**
     * This method is used as a callBack in AsyncTask to start the Home_Activity when login was successful
     * all the params are passed to the home_activity, so it immediately knows who is logged in and if it should show the in_plan or not_in_plan layout
     * @param _token the JWT token after login
     * @param _resUserName the userName taken from login response
     * @param _resUserPlanId the PlanId taken from login response ( == "null" if user in no plan ) => always null here, as user just was created
     * @param _planName the planName of the users plan ( == "null" if user is in no plan ) => always null here, as user just was created
     * @param _planOwner the planOwner of the users plan ( == "null" if user is in no plan ) => always null here, as user just was created
     * the null params still get send because otherwise the home_activity crashes.
     */
    @Override
    public void startHomeActivity(String _token, String _resUserName, String _resUserPlanId, String _planName, String _planOwner) {
        this.state = DebugState.REGISTERED_AND_LOGGED_IN;
        Intent intent = new Intent(registerActivity, HomeActivity.class);
        intent.putExtra("TOKEN", _token);
        intent.putExtra("USERNAME", _resUserName);
        intent.putExtra("USERPLANID", _resUserPlanId);
        intent.putExtra("PLANNAME", _planName);
        intent.putExtra("PLANOWNER", _planOwner);
        registerActivity.startActivity(intent);
        Log.d(TAG, "startHomeView: state == " + this.state);
    }

    @Override
    public void errorCallbackRegisterUser(String errorInfo) {
        registerButton.setEnabled(true);
        showToast(errorInfo);
    }

    @Override
    public void successCallbackRegisterUser(String _token, String _resUserName, String _resUserPlanId) {
            startHomeActivity(
                    _token,
                    _resUserName,
                    _resUserPlanId,
                    "null",
                    "null"
            );
    }
}
