package com.control.asyncLogic.removeUserFromPlan;

public interface RemoveUserCallback {
    void removeUserCallBack(String[] loginData, Boolean _deleteHimself);

    void removeUserCallAsync(String _token, String _userNameToRemove, RemoveUserUser removeUserUser, Boolean _deleteHimself);
}
