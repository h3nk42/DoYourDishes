package com.model.dataModel;

import java.math.BigInteger;

public class Task {
    private String name;
    private String plan;
    private Integer pointsWorth;
    private BigInteger lastTimeDone;
    private String taskId;

    public Task(String _name, String _plan, Integer _pointsWorth, BigInteger _lastTimeDone, String _taskid) {
        this.plan = _plan;
        this.name = _name;
        this.pointsWorth = _pointsWorth;
        this.lastTimeDone = _lastTimeDone;
        this.taskId = _taskid;
    }

    public Task()throws NullPointerException{
        throw new NullPointerException("parameterless constructor");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastTimeDone(BigInteger lastTimeDone) {
        this.lastTimeDone = lastTimeDone;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setPointsWorth(Integer pointsWorth) {
        guard(pointsWorth);
        this.pointsWorth = pointsWorth;
    }

    public String getName() {
        return name;
    }

    public BigInteger getLastTimeDone() {
        return lastTimeDone;
    }

    public Integer getPointsWorth() {
        return pointsWorth;
    }

    public String getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", plan='" + plan + '\'' +
                ", pointsWorth=" + pointsWorth +
                ", lastTimeDone=" + lastTimeDone +
                ", taskId='" + taskId + '\'' +
                '}';
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void guard(Integer points){
        //autounboxing/autoboxing
        if(points<=0){
            throw new IllegalArgumentException("Available Points have to be more than 0!");
        }
    }



}
