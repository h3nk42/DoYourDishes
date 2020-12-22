package com.control.asyncLogic.fetchPlan;

import com.model.dataModel.Task;
import com.model.dataModel.User;

import java.util.List;

class FetchPlanCallBackImpl implements FetchPlanCallback{

    FetchPlanUser fetchPlanUser;


    @Override
    public void fetchPlanCallBack(String[] planData,  List<User> users, List<Task> tasks) {
        String responseInfo = planData[0];
        if(responseInfo.equals("fetchPlanSuccess")){
            String resPlanOwner =  planData[1];
            String resPlanName = planData[2];
            String resPlanId = planData[3];
            fetchPlanUser.successCallbackFetchPlan(resPlanName, resPlanId, resPlanOwner, users, tasks);
        } else if (responseInfo.equals("fetchPlanException") || responseInfo.equals("fetchPlanError")) {
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
