package com.control.asyncLogic.fulfillTask;

public interface FulfillTaskUser {
    void successCallbackFulfillTask(String responseText);

    void errorCallbackFulfillTask(String errorInfo);
}
