package com.control.asyncLogic.deleteTask;


public class DeleteTaskFacadeImpl implements DeleteTaskFacade {

    private DeleteTaskCallback deleteTaskCallback;

    DeleteTaskFacadeImpl(DeleteTaskCallback deleteTaskCallback) {
        this.deleteTaskCallback = deleteTaskCallback;
    }


    @Override
    public void deleteTaskCallAsync(String _token, String _taskToDeleteId, DeleteTaskUser deleteTaskUser) {
        this.deleteTaskCallback.deleteTaskCallAsync(_token, _taskToDeleteId, deleteTaskUser);
    }
}
