package com.control.asyncLogic.createPlan;


import com.control.asyncLogic.fetchPlan.FetchPlanUser;

public class CreatePlanFacadeImpl implements CreatePlanFacade {

    private CreatePlanCallback createPlanCallback;

    CreatePlanFacadeImpl (CreatePlanCallback createPlanCallback) {
        this.createPlanCallback = createPlanCallback;
    }

    @Override
    public void createPlanCallBack(String[] createPlanData) {
        this.createPlanCallback.createPlanCallBack(createPlanData);
    }

    @Override
    public void createPlanCallAsync(String _token, String _planName, CreatePlanUser createPlanUser) {
        this.createPlanCallback.createPlanCallAsync(_token,_planName, createPlanUser);
    }
}
