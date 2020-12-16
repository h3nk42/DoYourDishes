package com.control.asyncLogic.fetchPlan;

import com.control.asyncLogic.fetchPlan.FetchPlanUser;
import com.control.controllerLogic.LoginController;


interface FetchPlanCallback {

    void fetchPlanCallBack(String[] planData);


    void fetchPlanCallAsync(String _token, FetchPlanUser fetchPlanUser);
}
