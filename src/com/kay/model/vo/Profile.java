package com.kay.model.vo;

import java.time.LocalTime;

public class Profile {
    private String icon; // icon url
    private String name;

    private String rideLevel;
    private String rideLevelExp;
    private String rideDistance;
    private String rideTime;
    private String rideElevation;
    private String rideCalories;

    private String walkLevel;
    private String walkLevelExp;
    private String walkDistance;
    private String walkTime;
    private String walkCalories;

    private String drops;

    public Profile(String icon, String name, String rideLevel, String rideLevelExp, String rideDistance, String rideTime, String rideElevation, String rideCalories, String walkLevel, String walkLevelExp, String walkDistance, String walkTime, String walkCalories, String drops) {
        this.icon = icon;
        this.name = name;
        this.rideLevel = rideLevel;
        this.rideLevelExp = rideLevelExp;
        this.rideDistance = rideDistance;
        this.rideTime = rideTime;
        this.rideElevation = rideElevation;
        this.rideCalories = rideCalories;
        this.walkLevel = walkLevel;
        this.walkLevelExp = walkLevelExp;
        this.walkDistance = walkDistance;
        this.walkTime = walkTime;
        this.walkCalories = walkCalories;
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
                "\n walkLevel='" + walkLevel + '\'' +
                "\n walkLevelExp='" + walkLevelExp + '\'' +
                "\n walkDistance='" + walkDistance + '\'' +
                "\n walkTime='" + walkTime + '\'' +
                "\n walkCalories='" + walkCalories + '\'' +
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

    public String getWalkLevel() {
        return walkLevel;
    }

    public void setWalkLevel(String walkLevel) {
        this.walkLevel = walkLevel;
    }

    public String getWalkLevelExp() {
        return walkLevelExp;
    }

    public void setWalkLevelExp(String walkLevelExp) {
        this.walkLevelExp = walkLevelExp;
    }

    public String getWalkDistance() {
        return walkDistance;
    }

    public void setWalkDistance(String walkDistance) {
        this.walkDistance = walkDistance;
    }

    public String getWalkTime() {
        return walkTime;
    }

    public void setWalkTime(String walkTime) {
        this.walkTime = walkTime;
    }

    public String getWalkCalories() {
        return walkCalories;
    }

    public void setWalkCalories(String walkCalories) {
        this.walkCalories = walkCalories;
    }

    public String getDrops() {
        return drops;
    }

    public void setDrops(String drops) {
        this.drops = drops;
    }
}
