package com.control.asyncLogic.deletePlan;

public interface DeletePlanFacade {

    void deletePlanCallBack(String[] planData);

    void deletePlanCallAsync(String _token, DeletePlanUser deletePlanUser);
}
