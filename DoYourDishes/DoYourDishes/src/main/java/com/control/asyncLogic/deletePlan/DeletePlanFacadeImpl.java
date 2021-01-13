package com.control.asyncLogic.deletePlan;


class DeletePlanFacadeImpl implements DeletePlanFacade {

    private DeletePlanCallback deletePlanCallback;

    DeletePlanFacadeImpl(DeletePlanCallback deletePlanCallback) {
        this.deletePlanCallback = deletePlanCallback;
    }


    @Override
    public void deletePlanCallBack(String[] planData) {
        this.deletePlanCallback.deletePlanCallBack(planData);
    }

    @Override
    public void deletePlanCallAsync(String _token, DeletePlanUser deletePlanUser) {
        this.deletePlanCallback.deletePlanCallAsync(_token, deletePlanUser);

    }
}
