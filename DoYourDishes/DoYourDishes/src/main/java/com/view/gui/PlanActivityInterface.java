package com.view.gui;

import android.view.View;

import com.control.controllerLogic.PlanLogic.PlanController;

public interface PlanActivityInterface {
    PlanController getPlanController();

    void addUser(View view);

    void addTask(View view);
}
