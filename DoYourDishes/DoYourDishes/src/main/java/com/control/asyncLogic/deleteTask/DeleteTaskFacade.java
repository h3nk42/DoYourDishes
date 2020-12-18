package com.control.asyncLogic.deleteTask;

public interface DeleteTaskFacade {
    void deleteTaskCallAsync(String _token, String _taskToDeleteId,  DeleteTaskUser deleteTaskUser);
}
