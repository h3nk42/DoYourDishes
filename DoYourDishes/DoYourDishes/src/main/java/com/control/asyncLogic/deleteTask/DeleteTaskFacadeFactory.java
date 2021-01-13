package com.control.asyncLogic.deleteTask;


public class DeleteTaskFacadeFactory {
    public static DeleteTaskFacade produceDeleteTaskFacade() {
        DeleteTaskCallback deleteTaskCallback = new DeleteTaskCallbackImpl();
        return new DeleteTaskFacadeImpl(deleteTaskCallback);
    }

}
