package com.control.asyncLogic.createPlan;

interface CreatePlanCallback {

    void createPlanCallBack(String[] planData);

    void createPlanCallAsync(String planOwner, String planName, CreatePlanUser createPlanUser);
}
