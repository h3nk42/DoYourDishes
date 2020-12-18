package com.control.asyncLogic.fulfillTask;

import com.control.asyncLogic.deleteTask.DeleteTaskCallback;
import com.control.asyncLogic.deleteTask.DeleteTaskCallbackImpl;
import com.control.asyncLogic.deleteTask.DeleteTaskFacade;
import com.control.asyncLogic.deleteTask.DeleteTaskFacadeImpl;

public class FulfillTaskFacadeFactory {

    public static FulfillTaskFacade produceFulfillTaskFacade(){
        FulfillTaskCallback fulfillTaskCallback = new FulfillTaskCallbackImpl();
        return new FulfillTaskFacadeImpl(fulfillTaskCallback);
    }
}
