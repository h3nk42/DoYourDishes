package com.control.asyncLogic.addUserToPlan;


public class AddUserCallbackImpl implements AddUserCallback {

    private AddUserUser addUserUser;

    @Override
    public void addUserCallBack(String[] planData) {
        String responseInfo = planData[0];
        if (responseInfo.equals("createPlanSuccess")) {
            String successMessage = "placeHolder";
            addUserUser.successCallbackAddUser(successMessage);
        } else if (responseInfo.equals("createPlanError") || responseInfo.equals("createPlanException")) {
            String errorInfo = planData[1];
            addUserUser.errorCallbackAddUser(errorInfo);
        }
    }

    @Override
    public void addUserCallAsync(String _token, String _userNameToAdd, AddUserUser addUserUser) {
        this.addUserUser = addUserUser;
        AsyncTaskAddUser asyncTaskAddUser = new AsyncTaskAddUser(_token, _userNameToAdd, this);
        asyncTaskAddUser.execute();
    }
}
