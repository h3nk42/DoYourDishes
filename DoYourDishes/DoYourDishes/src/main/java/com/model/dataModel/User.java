package com.model.dataModel;


public class User {
    private String userName;
    private String plan;
    private Integer pointsInPlan;

    public User(String _userName, String _plan, Integer pointsInPlan) {
        this.userName = _userName;
        this.plan = _plan;
        this.pointsInPlan = pointsInPlan;
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

    public Integer getPointsInPlan() {
        return pointsInPlan;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", plan='" + plan + '\'' +
                ", pointsInPlan=" + pointsInPlan +
                '}';
    }

    public void setPointsInPlan(Integer pointsInPlan) {
        this.pointsInPlan = pointsInPlan;
    }
}
