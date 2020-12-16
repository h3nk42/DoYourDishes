package com.control.controllerLogic;


import com.view.R;
import com.view.gui.PlanActivity;

public class PlanController {

    PlanActivity planActivity;

    public PlanController(PlanActivity planActivity){
        planActivity = planActivity;
    }


    public void openUsers(){
        planActivity.setContentView(R.layout.activity_plan_users);
    }
}
