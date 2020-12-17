package com.control.asyncLogic.deleteUser;

public interface DeleteUserCallback {
    void deleteUserCallBack(String[] planData);

    void deleteUserCallAsync(String _token, DeleteUserUser deleteUserUser);
}
