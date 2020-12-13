package com.model.dataModel;

import java.util.List;

public class Plan {
    private String owner;
    private String name;
    private String planId;
    private List<String> tasks;
    private List<String> users;

    public Plan(String _owner, String _name, String _planId, List<String> _users) {
        this.owner = _owner;
        this.name = _name;
        this.planId = _planId;
        this.users = _users;
    }

    public Plan(String _owner, String _name, String _planId, List<String> _users, List<String> _tasks) {
        this.owner = _owner;
        this.name = _name;
        this.planId = _planId;
        this.users = _users;
        this.tasks = _tasks;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public void setUsers(List<String>users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public List<String> getUsers() {
        return users;
    }
}
