package com.control.asyncLogic.addTaskToPlan;


public class AddTaskFacadeFactory {

    public static AddTaskFacade produceAddTaskFacade() {
        AddTaskCallback addTaskCallback = new AddTaskCallbackImpl();
        return new AddTaskFacadeImpl(addTaskCallback);
    }
}
