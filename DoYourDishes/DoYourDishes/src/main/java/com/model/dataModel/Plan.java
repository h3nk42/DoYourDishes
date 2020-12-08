package com.model.dataModel;

public class Plan {
    private String owner;
    private String name;
    private Task[] tasks;
    private User[] users;

    public Plan(String _owner, String _name) {
        this.owner = _owner;
        this.name = _name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public User[] getUsers() {
        return users;
    }
}
