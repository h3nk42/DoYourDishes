package com.control.asyncLogic.addUserToPlan;


public class AddUserFacadeImpl implements AddUserFacade {

    private AddUserCallback addUserCallback;

    AddUserFacadeImpl(AddUserCallback addUserCallback) {
        this.addUserCallback = addUserCallback;
    }

    @Override
    public void addUserCallAsync(String _token, String _userNameToAdd, AddUserUser addUserUser) {
        this.addUserCallback.addUserCallAsync(_token, _userNameToAdd, addUserUser);
    }
}
