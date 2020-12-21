package com.view.gui.fragments;

import com.model.dataModel.User;

import java.util.List;

public interface UsersFragmentInterface {
    void renderData(List<User> usersToRender);
    void addUser();
}
