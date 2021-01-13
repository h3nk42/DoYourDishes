package com.control.asyncLogic.fulfillTask;

public class FulfillTaskCallbackImpl implements FulfillTaskCallback {

    FulfillTaskUser fulfillTaskUser;

    @Override
    public void fulfillTaskCallBack(String[] planData) {

        String responseInfo = planData[0];
        if (responseInfo.equals("fulfillTaskSuccess")) {
            fulfillTaskUser.successCallbackFulfillTask(responseInfo);
        } else if (responseInfo.equals("fulfillTaskError") || responseInfo.equals("fulfillTaskException")) {
            String errorInfo = planData[1];
            fulfillTaskUser.errorCallbackFulfillTask(errorInfo);
        }
    }

    @Override
    public void fulfillTaskCallAsync(String _token, String _taskToFulfillId, FulfillTaskUser fulfillTaskUser) {
        this.fulfillTaskUser = fulfillTaskUser;
        AsyncTaskFulfillTask asyncTaskFulfillTask = new AsyncTaskFulfillTask(_token, _taskToFulfillId, this);
        asyncTaskFulfillTask.execute();
    }
}
