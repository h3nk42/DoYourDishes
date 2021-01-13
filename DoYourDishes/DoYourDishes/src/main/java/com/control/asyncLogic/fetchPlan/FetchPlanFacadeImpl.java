package com.control.asyncLogic.fetchPlan;

class FetchPlanFacadeImpl implements FetchPlanFacade {

    private FetchPlanCallback fetchPlanCallback;

    FetchPlanFacadeImpl(FetchPlanCallback _fetchPlanCallback) {
        this.fetchPlanCallback = _fetchPlanCallback;
    }


    @Override
    public void fetchPlanCallAsync(String _token, FetchPlanUser fetchPlanUser) {
        this.fetchPlanCallback.fetchPlanCallAsync(_token, fetchPlanUser);
    }

}
