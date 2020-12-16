package com.control.asyncLogic.createPlan;

import com.control.asyncLogic.fetchPlan.FetchPlanUser;

interface CreatePlanCallback {

    void createPlanCallBack(String[] planData);

    void createPlanCallAsync(String planOwner,String planName, CreatePlanUser createPlanUser);
}
