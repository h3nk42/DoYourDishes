package com.control.asyncLogic.addUserToPlan;

import com.control.asyncLogic.createPlan.CreatePlanUser;

public interface AddUserFacade {

    void addUserCallAsync(String _token, String _userNameToAdd, AddUserUser addUserUser);
}
