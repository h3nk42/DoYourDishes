package com.control.asyncLogic.createPlan;

public class CreatePlanFacadeFactory {

    public static CreatePlanFacade produceCreatePlanFacade(){
        CreatePlanCallback createPlanCallback = new CreatePlanCallbackImpl();
        return new CreatePlanFacadeImpl(createPlanCallback);
    }
}
