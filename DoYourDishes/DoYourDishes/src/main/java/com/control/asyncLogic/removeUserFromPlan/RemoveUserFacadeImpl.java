package com.control.asyncLogic.removeUserFromPlan;



public class RemoveUserFacadeImpl implements RemoveUserFacade {

    private RemoveUserCallback removeUserCallback;

    RemoveUserFacadeImpl (RemoveUserCallback removeUserCallback) {
        this.removeUserCallback = removeUserCallback;
    }

    @Override
    public void removeUserCallAsync(String _token, String _userNameToRemove, RemoveUserUser removeUserUser) {
        this.removeUserCallback.removeUserCallAsync(_token,_userNameToRemove, removeUserUser);
    }
}
