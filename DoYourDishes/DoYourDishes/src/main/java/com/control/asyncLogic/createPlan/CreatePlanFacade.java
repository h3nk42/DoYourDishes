package com.control.asyncLogic.createPlan;

import com.control.asyncLogic.addUserToPlan.AddUserUser;
import com.control.asyncLogic.fetchPlan.FetchPlanUser;

public interface CreatePlanFacade {

    void createPlanCallBack(String[] loginData);


    void createPlanCallAsync(String _token,String _planName, CreatePlanUser createPlanUser);
}
