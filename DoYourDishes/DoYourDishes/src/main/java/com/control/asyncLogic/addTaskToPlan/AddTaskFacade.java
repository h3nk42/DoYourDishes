package com.control.asyncLogic.addTaskToPlan;

public interface AddTaskFacade {
    void addTaskCallAsync(String _token, String _userNameToAdd, String _taskPointsWorth, AddTaskUser addUserUser);
}
