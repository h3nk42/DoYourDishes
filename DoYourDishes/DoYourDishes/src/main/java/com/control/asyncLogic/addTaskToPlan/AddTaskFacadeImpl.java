package com.control.asyncLogic.addTaskToPlan;

class AddTaskFacadeImpl implements AddTaskFacade {

    private final AddTaskCallback addTaskCallback;

    AddTaskFacadeImpl(AddTaskCallback addTaskCallback) {
        this.addTaskCallback = addTaskCallback;
    }

    @Override
    public void addTaskCallAsync(String _token, String _taskNameToAdd, String _taskPointsWorth, AddTaskUser addTaskUser) {
        this.addTaskCallback.addUserCallAsync(_token, _taskNameToAdd, _taskPointsWorth, addTaskUser);
    }
}
