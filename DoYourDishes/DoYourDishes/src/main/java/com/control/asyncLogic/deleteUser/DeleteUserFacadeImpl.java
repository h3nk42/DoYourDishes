package com.control.asyncLogic.deleteUser;


class DeleteUserFacadeImpl implements DeleteUserFacade {

    private DeleteUserCallback deleteUserCallback;

    DeleteUserFacadeImpl(DeleteUserCallback deleteUserCallback) {
        this.deleteUserCallback = deleteUserCallback;
    }


    @Override
    public void deleteUserCallBack(String[] delUserData) {
        this.deleteUserCallback.deleteUserCallBack(delUserData);
    }

    @Override
    public void deleteUserCallAsync(String _token, DeleteUserUser deleteUserUser) {
        this.deleteUserCallback.deleteUserCallAsync(_token, deleteUserUser);

    }
}
