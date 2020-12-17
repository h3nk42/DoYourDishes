package com.control.asyncLogic.removeUserFromPlan;

public interface RemoveUserFacade {
    void removeUserCallAsync(String _token, String _userNameToRemove, RemoveUserUser removeUserUser);
}
