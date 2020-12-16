package com.control.asyncLogic.fetchPlan;

public interface FetchPlanUser {

    public void successCallbackFetchPlan(String _planName, String _planOwner);

    public void errorCallbackFetchPlan(String errorInfo);
}
