package com.control.asyncLogic.fulfillTask;


public class FulfillTaskFacadeImpl implements FulfillTaskFacade {

    private FulfillTaskCallback fulfillTaskCallback;

    FulfillTaskFacadeImpl(FulfillTaskCallback fulfillTaskCallback) {
        this.fulfillTaskCallback = fulfillTaskCallback;
    }


    @Override
    public void fulfillTaskCallAsync(String _token, String _taskToFulfillId, FulfillTaskUser fulfillTaskUser) {
        this.fulfillTaskCallback.fulfillTaskCallAsync(_token, _taskToFulfillId, fulfillTaskUser);
    }
}
