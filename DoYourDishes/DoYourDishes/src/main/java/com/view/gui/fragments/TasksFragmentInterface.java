package com.view.gui.fragments;

import com.model.dataModel.Task;

import java.util.List;

public interface TasksFragmentInterface {
    void renderData(List<Task> tasksToRender);

    void addTask();
}
