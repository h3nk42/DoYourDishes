package com.control.asyncLogic.deleteTask;

public interface DeleteTaskCallback {
    void deleteTaskCallBack(String[] planData);

    void deleteTaskCallAsync(String _token, String _taskToDeleteId, DeleteTaskUser deleteTaskUser);
}
