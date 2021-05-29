package com.kay.model.vo;

public class Profile {
    private String icon; // icon url
    private String name;

    private String rideLevel;
    private String rideLevelExp;
    private String rideDistance;
    private String rideTime;
    private String rideElevation;
    private String rideCalories;

    private String runLevel;
    private String runLevelExp;
    private String runDistance;
    private String runTime;
    private String runCalories;

    private String drops;

    public Profile(String icon, String name, String rideLevel, String rideLevelExp, String rideDistance, String rideTime, String rideElevation, String rideCalories, String runLevel, String runLevelExp, String runDistance, String runTime, String runCalories, String drops) {
        this.icon = icon;
        this.name = name;
        this.rideLevel = rideLevel;
        this.rideLevelExp = rideLevelExp;
        this.rideDistance = rideDistance;
        this.rideTime = rideTime;
        this.rideElevation = rideElevation;
        this.rideCalories = rideCalories;
        this.runLevel = runLevel;
        this.runLevelExp = runLevelExp;
        this.runDistance = runDistance;
        this.runTime = runTime;
        this.runCalories = runCalories;
        this.drops = drops;
    }

    @Override
    public String toString() {
        return "Profile {\n " +
                "icon='" + icon + '\'' +
                "\n name='" + name + '\'' +
                "\n rideLevel='" + rideLevel + '\'' +
                "\n rideLevelExp='" + rideLevelExp + '\'' +
                "\n rideDistance='" + rideDistance + '\'' +
                "\n rideTime='" + rideTime + '\'' +
                "\n rideElevation='" + rideElevation + '\'' +
                "\n rideCalories='" + rideCalories + '\'' +
                "\n runLevel='" + runLevel + '\'' +
                "\n runLevelExp='" + runLevelExp + '\'' +
                "\n runDistance='" + runDistance + '\'' +
                "\n runTime='" + runTime + '\'' +
                "\n runCalories='" + runCalories + '\'' +
                "\n drops='" + drops + '\'' +
                "\n}";
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRideLevel() {
        return rideLevel;
    }

    public void setRideLevel(String rideLevel) {
        this.rideLevel = rideLevel;
    }

    public String getRideLevelExp() {
        return rideLevelExp;
    }

    public void setRideLevelExp(String rideLevelExp) {
        this.rideLevelExp = rideLevelExp;
    }

    public String getRideDistance() {
        return rideDistance;
    }

    public void setRideDistance(String rideDistance) {
        this.rideDistance = rideDistance;
    }

    public String getRideTime() {
        return rideTime;
    }

    public void setRideTime(String rideTime) {
        this.rideTime = rideTime;
    }

    public String getRideElevation() {
        return rideElevation;
    }

    public void setRideElevation(String rideElevation) {
        this.rideElevation = rideElevation;
    }

    public String getRideCalories() {
        return rideCalories;
    }

    public void setRideCalories(String rideCalories) {
        this.rideCalories = rideCalories;
    }

    public String getRunLevel() {
        return runLevel;
    }

    public void setRunLevel(String runLevel) {
        this.runLevel = runLevel;
    }

    public String getRunLevelExp() {
        return runLevelExp;
    }

    public void setRunLevelExp(String runLevelExp) {
        this.runLevelExp = runLevelExp;
    }

    public String getRunDistance() {
        return runDistance;
    }

    public void setRunDistance(String runDistance) {
        this.runDistance = runDistance;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getRunCalories() {
        return runCalories;
    }

    public void setRunCalories(String runCalories) {
        this.runCalories = runCalories;
    }

    public String getDrops() {
        return drops;
    }

    public void setDrops(String drops) {
        this.drops = drops;
    }
}
