package com.control.asyncLogic.fetchPlan;
import com.control.asyncLogic.fetchPlan.FetchPlanUser;
import com.control.controllerLogic.LoginController;


public interface FetchPlanFacade {

    void fetchPlanCallBack(String[] loginData);


    void fetchPlanCallAsync(String _token, FetchPlanUser fetchPlanUser);
}
