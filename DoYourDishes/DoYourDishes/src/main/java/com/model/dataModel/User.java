package com.model.dataModel;


public class User {
    private String userName;
    private String password;
    private String plan;

    public User(String _userName, String _password, String _plan) {
        this.userName = _userName;
        this.password = _password;
        this.plan = _plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlan() {
        return plan;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
