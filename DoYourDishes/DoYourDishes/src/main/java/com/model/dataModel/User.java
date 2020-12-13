package com.model.dataModel;


public class User {
    private String userName;
    private String plan;

    public User(String _userName, String _plan) {
        this.userName = _userName;
        this.plan = _plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlan() {
        return plan;
    }


    public String getUserName() {
        return userName;
    }
}
