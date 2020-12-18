package com.control.asyncLogic.fulfillTask;

public interface FulfillTaskFacade {
    void fulfillTaskCallAsync(String _token, String _taskToDeleteId, FulfillTaskUser fulfillTaskUser);
}
