package com.control.controllerLogic.PlanLogic;

public interface PlanControllerInterface {

    void showToast(String responseText);

    String getToken();

    void fetchData();

    void finishPlanActivity();

    String getActiveUserName();
}
