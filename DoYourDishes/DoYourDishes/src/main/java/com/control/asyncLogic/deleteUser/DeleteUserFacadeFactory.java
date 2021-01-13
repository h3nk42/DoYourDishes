package com.control.asyncLogic.deleteUser;

public class DeleteUserFacadeFactory {

    public static DeleteUserFacade produceDeleteUserFacade() {
        DeleteUserCallback deleteUserCallback = new DeleteUserCallbackImpl();
        return new DeleteUserFacadeImpl(deleteUserCallback);
    }

}
