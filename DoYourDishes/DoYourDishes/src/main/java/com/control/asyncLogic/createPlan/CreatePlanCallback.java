package com.control.asyncLogic.createPlan;

import com.control.asyncLogic.fetchPlan.FetchPlanUser;

interface CreatePlanCallback {

    void createPlanCallBack(String[] planData);

    /**
     *  is called when the asyncTask fetch plan should be started/executed
     */
    void createPlanCallAsync(String planOwner,String planName, CreatePlanUser createPlanUser);
}
