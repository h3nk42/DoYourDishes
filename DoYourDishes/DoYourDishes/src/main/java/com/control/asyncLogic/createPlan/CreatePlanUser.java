package com.control.asyncLogic.createPlan;

public interface CreatePlanUser {
     void successCallbackCreatePlan(String _planOwner,String _planName, String _planId);

     void errorCallbackCreatePlan(String errorInfo);
}
