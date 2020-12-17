package com.control.asyncLogic.removeUserFromPlan;

public class RemoveUserCallbackImpl implements RemoveUserCallback {

    RemoveUserUser removeUserUser;

    private String resToken;
    private String respUserName;
    private String responsePlanId;

    @Override
    public void removeUserCallBack(String[] responseData) {

        String responseInfo = responseData[0];
        if(responseInfo.equals("removeUserSuccess")){
            removeUserUser.successCallbackRemoveUser(responseInfo);
        } else if (responseInfo.equals("removeUserError") || responseInfo.equals("removeUserException")) {
            String errorInfo = responseData[1];
            removeUserUser.errorCallbackRemoveUser(errorInfo);
        }
    }

    @Override
    public void removeUserCallAsync(String _token, String _userNameToRemove, RemoveUserUser removeUserUser) {
        this.removeUserUser = removeUserUser;
        AsyncTaskRemoveUser asyncTaskRemoveUser = new AsyncTaskRemoveUser(_token, _userNameToRemove, this);
        asyncTaskRemoveUser.execute();
    }
}
