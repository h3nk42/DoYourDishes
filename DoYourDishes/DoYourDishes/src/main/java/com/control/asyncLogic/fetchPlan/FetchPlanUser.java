package com.control.asyncLogic.fetchPlan;

import com.model.dataModel.User;

import java.util.List;

public interface FetchPlanUser {

    public void successCallbackFetchPlan(String _planName, String _planOwner,  List<User> users);

    public void errorCallbackFetchPlan(String errorInfo);
}
