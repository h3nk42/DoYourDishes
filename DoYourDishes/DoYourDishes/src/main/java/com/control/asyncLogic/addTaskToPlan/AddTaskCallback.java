package com.control.asyncLogic.addTaskToPlan;

interface AddTaskCallback {
    void addTaskCallBack(String[] planData);

    void addUserCallAsync(String _token, String _userNameToAdd, String _taskPointsWorth, AddTaskUser addTaskUser);
}
