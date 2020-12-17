package com.control.asyncLogic.fetchPlan;

import com.model.dataModel.User;

import java.util.List;

class FetchPlanFacadeImpl implements FetchPlanFacade {

    private FetchPlanCallback fetchPlanCallback;

    FetchPlanFacadeImpl (FetchPlanCallback _fetchPlanCallback) {
        this.fetchPlanCallback = _fetchPlanCallback;
    }


    @Override
    public void fetchPlanCallAsync(String _token, FetchPlanUser fetchPlanUser) {
        this.fetchPlanCallback.fetchPlanCallAsync(_token, fetchPlanUser);
    }

}
