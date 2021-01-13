package com.control.asyncLogic.deleteUser;

public class DeleteUserCallbackImpl implements DeleteUserCallback {


    DeleteUserUser deleteUserUser;

    @Override
    public void deleteUserCallBack(String[] planData) {

        String responseInfo = planData[0];
        if (responseInfo.equals("deleteUserSuccess")) {
            deleteUserUser.successCallbackDeleteUser(responseInfo);
        } else if (responseInfo.equals("deleteUserError") || responseInfo.equals("deleteUserException")) {
            String errorInfo = planData[1];
            deleteUserUser.errorCallbackDeleteUser(errorInfo);
        }
    }

    @Override
    public void deleteUserCallAsync(String _token, DeleteUserUser deleteUserUser) {
        this.deleteUserUser = deleteUserUser;
        AsyncTaskDeleteUser asyncTaskDeleteUser = new AsyncTaskDeleteUser(_token, this);
        asyncTaskDeleteUser.execute();
    }
}
