package com.control.asyncLogic.addUserToPlan;


public interface AddUserCallback {
    void addUserCallBack(String[] planData);

    void addUserCallAsync(String _token, String _userNameToAdd, AddUserUser addUserUser);
}
