package com.control.asyncLogic.fetchPlan;

import com.model.dataModel.User;

import java.util.List;

class FetchPlanCallBackImpl implements FetchPlanCallback{

    FetchPlanUser fetchPlanUser;


    @Override
    public void fetchPlanCallBack(String[] planData,  List<User> users) {
        String responseInfo = planData[0];
        if(responseInfo.equals("fetchPlanSuccess")){
            String resPlanOwner =  planData[1];
            String resPlanName = planData[2];
            fetchPlanUser.successCallbackFetchPlan(resPlanName, resPlanOwner, users);
        } else if (responseInfo.equals("registerException")) {
            String errorInfo = planData[1];
            fetchPlanUser.errorCallbackFetchPlan(errorInfo);
        }
    }

    @Override
    public void fetchPlanCallAsync(String _token, FetchPlanUser fetchPlanUser) {
        this.fetchPlanUser = fetchPlanUser;
        AsyncTaskFetchPlan request = new AsyncTaskFetchPlan(_token, this);
        request.execute();
    }
}
