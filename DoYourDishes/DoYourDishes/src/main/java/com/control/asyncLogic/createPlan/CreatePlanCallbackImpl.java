package com.control.asyncLogic.createPlan;

import com.control.asyncLogic.AsyncTask;
import com.control.asyncLogic.fetchPlan.FetchPlanUser;

public class CreatePlanCallbackImpl implements CreatePlanCallback {

    private CreatePlanUser createPlanUser;

    @Override
    public void createPlanCallBack(String[] planData) {
        String responseInfo = planData[0];
        if(responseInfo.equals("createPlanSuccess")){
            String resPlanOwner = planData[1];
            String resPlanName =  planData[2];
            String resPlanId = planData[3];
            createPlanUser.successCallbackCreatePlan(resPlanOwner, resPlanName, resPlanId);
        } else if (responseInfo.equals("createPlanError") || responseInfo.equals("createPlanException")) {
            String errorInfo = planData[1];
            createPlanUser.errorCallbackCreatePlan(errorInfo);
        }
    }

    @Override
    public void createPlanCallAsync(String _token, String _planName, CreatePlanUser createPlanUser) {
        this.createPlanUser = createPlanUser;
        AsyncTaskCreatePlan asyncTaskCreatePlan = new AsyncTaskCreatePlan(_token, _planName, this);
        asyncTaskCreatePlan.execute();
    }
}
