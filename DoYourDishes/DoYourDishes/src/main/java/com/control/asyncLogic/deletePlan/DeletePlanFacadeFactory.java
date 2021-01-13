package com.control.asyncLogic.deletePlan;


public class DeletePlanFacadeFactory {

    public static DeletePlanFacade produceDeletePlanFacade() {
        DeletePlanCallback deletePlanCallback = new DeletePlanCallbackImpl();
        return new DeletePlanFacadeImpl(deletePlanCallback);
    }
}
