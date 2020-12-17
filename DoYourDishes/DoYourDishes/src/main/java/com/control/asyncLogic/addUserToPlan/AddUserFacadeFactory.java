package com.control.asyncLogic.addUserToPlan;


public class AddUserFacadeFactory {

    public static AddUserFacade produceAddUserFacade(){
        AddUserCallback addUserCallback = new AddUserCallbackImpl();
        return new AddUserFacadeImpl(addUserCallback);
    }

}
