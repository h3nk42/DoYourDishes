package com.control.asyncLogic.fetchPlan;


public interface FetchPlanFacade {

    void fetchPlanCallBack(String[] loginData);


    void fetchPlanCallAsync(String _token, FetchPlanUser fetchPlanUser);
}
