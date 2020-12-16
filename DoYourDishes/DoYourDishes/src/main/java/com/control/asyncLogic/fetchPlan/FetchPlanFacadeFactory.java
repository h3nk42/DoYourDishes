package com.control.asyncLogic.fetchPlan;


public class FetchPlanFacadeFactory {

    public static FetchPlanFacade produceFetchPlanFacade(){
        FetchPlanCallback fetchPlanCallback = new FetchPlanCallBackImpl();
        return new FetchPlanFacadeImpl(fetchPlanCallback);
    }
}
