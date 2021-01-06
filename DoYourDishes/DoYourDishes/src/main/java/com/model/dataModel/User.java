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

    public User()throws NullPointerException{
        throw new NullPointerException();
    }

    public void setPlan(String plan) {

        guard(plan);

        this.plan = plan;
    }

    public void setUserName(String userName) {
        guard(userName);
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
        guard(pointsInPlan);
        this.pointsInPlan = pointsInPlan;
    }

    public void guard(String wennNull){
        if (wennNull==null)throw new NullPointerException();
    }

    public void guard(Integer wennNull){
        if (wennNull<0||wennNull==0) throw new IllegalArgumentException();
    }




}
