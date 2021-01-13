package com.control.asyncLogic.deletePlan;

interface DeletePlanCallback {

    void deletePlanCallBack(String[] planData);

    void deletePlanCallAsync(String _token, DeletePlanUser deletePlanUser);
}
