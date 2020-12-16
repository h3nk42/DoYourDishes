package com.control.asyncLogic.deletePlan;

import com.control.asyncLogic.fetchPlan.FetchPlanUser;

interface DeletePlanCallback {

    void deletePlanCallBack(String[] planData);

    void deletePlanCallAsync(String _token, DeletePlanUser deletePlanUser);
}
