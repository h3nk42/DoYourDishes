package com.control.asyncLogic.createPlan;

public interface CreatePlanFacade {

    void createPlanCallBack(String[] loginData);


    void createPlanCallAsync(String _token, String _planName, CreatePlanUser createPlanUser);
}
