package com.control.asyncLogic.deleteUser;

public interface DeleteUserFacade {
    void deleteUserCallBack(String[] delUserData);

    void deleteUserCallAsync(String _token, DeleteUserUser deleteUserUser);
}
