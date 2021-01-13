package com.control.asyncLogic.deleteTask;

public class DeleteTaskCallbackImpl implements DeleteTaskCallback {


    DeleteTaskUser deleteTaskUser;

    @Override
    public void deleteTaskCallBack(String[] planData) {

        String responseInfo = planData[0];
        if (responseInfo.equals("deleteTaskSuccess")) {
            deleteTaskUser.successCallbackDeleteTask(responseInfo);
        } else if (responseInfo.equals("deleteTaskError") || responseInfo.equals("deleteTaskException")) {
            String errorInfo = planData[1];
            deleteTaskUser.errorCallbackDeleteTask(errorInfo);
        }
    }

    @Override
    public void deleteTaskCallAsync(String _token, String _taskToDeleteId, DeleteTaskUser deleteTaskUser) {
        this.deleteTaskUser = deleteTaskUser;
        AsyncTaskDeleteTask asyncTaskDeleteTask = new AsyncTaskDeleteTask(_token, _taskToDeleteId, this);
        asyncTaskDeleteTask.execute();
    }
}
