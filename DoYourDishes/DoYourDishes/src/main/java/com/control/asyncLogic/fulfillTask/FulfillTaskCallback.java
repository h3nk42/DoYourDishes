package com.control.asyncLogic.fulfillTask;

public interface FulfillTaskCallback {
    void fulfillTaskCallBack(String[] planData);

    void fulfillTaskCallAsync(String _token, String _taskToFulfillId, FulfillTaskUser fulfillTaskUser);
}
