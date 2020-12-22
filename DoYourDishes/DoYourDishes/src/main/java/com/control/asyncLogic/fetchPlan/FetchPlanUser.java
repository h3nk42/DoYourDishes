package com.control.asyncLogic.fetchPlan;

import com.model.dataModel.Task;
import com.model.dataModel.User;

import java.util.List;

public interface FetchPlanUser {

    public void successCallbackFetchPlan(String _planName, String _planId, String _planOwner,  List<User> users, List<Task> tasks);

    public void errorCallbackFetchPlan(String errorInfo);
}
