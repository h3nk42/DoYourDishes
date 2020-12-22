package com.control.controllerLogic.HomeLogic;

public interface HomeControllerInterface {

  /*  void whoAmI();*/

    void renderLayout();

    void changeLayout(String whichLayout);

    void finishPrevActivities();

    void deletePlan();

    void createPlanDialog();

    void showToast(String responseText);

    void openPlanActivity();

    void deleteUser();

    void goBackToLandingActivity();

    void refreshData();
}
