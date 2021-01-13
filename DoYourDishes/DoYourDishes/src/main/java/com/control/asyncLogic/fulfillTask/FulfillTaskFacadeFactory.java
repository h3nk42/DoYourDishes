package com.control.asyncLogic.fulfillTask;

public class FulfillTaskFacadeFactory {

    public static FulfillTaskFacade produceFulfillTaskFacade() {
        FulfillTaskCallback fulfillTaskCallback = new FulfillTaskCallbackImpl();
        return new FulfillTaskFacadeImpl(fulfillTaskCallback);
    }
}
