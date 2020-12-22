package com.model.dataModel;

import java.util.List;

public class Plan {
    private String owner;
    private String name;
    private String planId;
    private List<Task> tasks;
    private List<User> users;

    public Plan(String _owner, String _name, String _planId, List<User> _users, List<Task> _tasks) {
        this.owner = _owner;
        this.name = _name;
        this.planId = _planId;
        this.users = _users;
        this.tasks = _tasks;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setUsers(List<User>users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<User> getUsers() {
        return users;
    }
}
