package com.model.dataModel;

public class Task {
    private String name;
    private String plan;
    private Integer pointsWorth;
    private Integer lastTimeDone;

    public Task(String _name, String _plan, Integer _pointsWorth, Integer _lastTimeDone) {
        this.plan = _plan;
        this.name = _name;
        this.pointsWorth = _pointsWorth;
        this.lastTimeDone = _lastTimeDone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastTimeDone(Integer lastTimeDone) {
        this.lastTimeDone = lastTimeDone;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setPointsWorth(Integer pointsWorth) {
        this.pointsWorth = pointsWorth;
    }

    public String getName() {
        return name;
    }

    public Integer getLastTimeDone() {
        return lastTimeDone;
    }

    public Integer getPointsWorth() {
        return pointsWorth;
    }

    public String getPlan() {
        return plan;
    }
}
