package com.control.asyncLogic.addTaskToPlan;



public class AddTaskCallbackImpl implements  AddTaskCallback{

    private AddTaskUser addTaskUser;

    @Override
    public void addTaskCallBack(String[] taskData) {
        String responseInfo = taskData[0];
        if(responseInfo.equals("addTaskSuccess")){
            String successMessage = "placeHolder";
            addTaskUser.successCallbackAddTask(successMessage);
        } else if (responseInfo.equals("addTaskError") || responseInfo.equals("addTaskException")) {
            String errorInfo = taskData[1];
            addTaskUser.errorCallbackAddTask(errorInfo);
        }
    }

    @Override
    public void addUserCallAsync(String _token, String _userNameToAdd, String _taskPointsWorth,  AddTaskUser addTaskUser) {
        this.addTaskUser = addTaskUser;
        AsyncTaskAddTask asyncTaskAddTask = new AsyncTaskAddTask(_token, _userNameToAdd, _taskPointsWorth, this);
        asyncTaskAddTask.execute();
    }
}
