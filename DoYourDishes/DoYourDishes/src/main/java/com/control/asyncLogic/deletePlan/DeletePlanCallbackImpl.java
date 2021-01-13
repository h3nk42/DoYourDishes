package com.control.asyncLogic.deletePlan;

class DeletePlanCallbackImpl implements DeletePlanCallback {

    private DeletePlanUser deletePlanUser;


    @Override
    public void deletePlanCallBack(String[] planData) {

        String responseInfo = planData[0];
        if (responseInfo.equals("deletePlanSuccess")) {
            deletePlanUser.successCallbackDeletePlan(responseInfo);
        } else if (responseInfo.equals("deletePlanError") || responseInfo.equals("deletePlanException")) {
            String errorInfo = planData[1];
            deletePlanUser.errorCallbackDeletePlan(errorInfo);
        }
    }

    @Override
    public void deletePlanCallAsync(String _token, DeletePlanUser deletePlanUser) {
        this.deletePlanUser = deletePlanUser;
        AsyncTaskDeletePlan asyncTaskDeletePlan = new AsyncTaskDeletePlan(_token, this);
        asyncTaskDeletePlan.execute();
    }
}
