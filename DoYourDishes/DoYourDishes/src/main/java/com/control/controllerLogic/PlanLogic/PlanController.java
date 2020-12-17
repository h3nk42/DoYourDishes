package com.control.controllerLogic.PlanLogic;

import com.model.dataModel.User;

import com.view.gui.PlanActivity;

public class PlanController {

    public static String readmyString = "user array";
    private final String token;
    public static String userPlanName;
    private final String userName;
    private final String userPlanId;
    private final String userPlanOwner;
    private final User activeUser;
    PlanActivity planActivity;

    public PlanController(String _token, String _planName, String _userName, String _userPlanId, String _userPlanOwner, PlanActivity planActivity){
        this.planActivity = planActivity;
        this.token = _token;
        this.userPlanName = _planName;
        this.userName = _userName;
        this.userPlanId = _userPlanId;
        this.userPlanOwner = _userPlanOwner;
        this.activeUser = new User(userName,userPlanId, 0);
    }

    public void openUsers(){

    }
}
