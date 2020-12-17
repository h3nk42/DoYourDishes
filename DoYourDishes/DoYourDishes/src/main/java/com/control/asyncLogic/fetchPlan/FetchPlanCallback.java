package com.control.asyncLogic.fetchPlan;


interface FetchPlanCallback {

    void fetchPlanCallBack(String[] planData);


    void fetchPlanCallAsync(String _token, FetchPlanUser fetchPlanUser);
}
