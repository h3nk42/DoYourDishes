package com.control.asyncLogic.addTaskToPlan;

import com.control.asyncLogic.addUserToPlan.AddUserCallback;
import com.control.asyncLogic.addUserToPlan.AddUserUser;

public class AddTaskFacadeImpl implements AddTaskFacade{

    private AddTaskCallback addTaskCallback;

    AddTaskFacadeImpl (AddTaskCallback addTaskCallback) {
        this.addTaskCallback = addTaskCallback;
    }

    @Override
    public void addTaskCallAsync(String _token, String _taskNameToAdd, String _taskPointsWorth, AddTaskUser addTaskUser) {
        this.addTaskCallback.addUserCallAsync(_token, _taskNameToAdd, _taskPointsWorth, addTaskUser);
    }
}
