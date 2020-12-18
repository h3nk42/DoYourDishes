package com.control.asyncLogic.fetchPlan;


import com.model.dataModel.Task;
import com.model.dataModel.User;

import java.util.List;

interface FetchPlanCallback {

    void fetchPlanCallBack(String[] planData,  List<User> users,  List<Task> tasks);


    void fetchPlanCallAsync(String _token, FetchPlanUser fetchPlanUser);
}
