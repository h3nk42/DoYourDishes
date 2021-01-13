package com.control.asyncLogic.removeUserFromPlan;

public class RemoveUserCallbackImpl implements RemoveUserCallback {

    RemoveUserUser removeUserUser;


    @Override
    public void removeUserCallBack(String[] responseData, Boolean _deleteHimself) {

        String responseInfo = responseData[0];
        if (responseInfo.equals("removeUserSuccess")) {
            removeUserUser.successCallbackRemoveUser(responseInfo, _deleteHimself);
        } else if (responseInfo.equals("removeUserError") || responseInfo.equals("removeUserException")) {
            String errorInfo = responseData[1];
            removeUserUser.errorCallbackRemoveUser(errorInfo);
        }
    }

    @Override
    public void removeUserCallAsync(String _token, String _userNameToRemove, RemoveUserUser removeUserUser, Boolean _deleteHimself) {
        this.removeUserUser = removeUserUser;
        AsyncTaskRemoveUser asyncTaskRemoveUser = new AsyncTaskRemoveUser(_token, _userNameToRemove, this, _deleteHimself);
        asyncTaskRemoveUser.execute();
    }
}
