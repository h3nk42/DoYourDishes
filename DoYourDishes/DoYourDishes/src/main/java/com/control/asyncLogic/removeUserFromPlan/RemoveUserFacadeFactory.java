package com.control.asyncLogic.removeUserFromPlan;


public class RemoveUserFacadeFactory {

    public static RemoveUserFacade produceRemoveUserFacade(){
        RemoveUserCallback removeUserCallback = new RemoveUserCallbackImpl();
        return new RemoveUserFacadeImpl(removeUserCallback);
    }

}
