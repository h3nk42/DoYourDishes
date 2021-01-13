package com.control.asyncLogic.addUserToPlan;

public interface AddUserFacade {

    void addUserCallAsync(String _token, String _userNameToAdd, AddUserUser addUserUser);
}
