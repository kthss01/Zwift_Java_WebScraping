package com.kay.model.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Activity {
    private String image; // 이미지 url
    private String date;
    private String rideon;
    private String name;
    private String distance;
    private String time;
    private String calories;

    public Activity(String image, String date, String rideon, String name, String distance, String time, String calories) {
        this.image = image;
        this.date = date;
        this.rideon = rideon;
        this.name = name;
        this.distance = distance;
        this.time = time;
        this.calories = calories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRideon() {
        return rideon;
    }

    public void setRideon(String rideon) {
        this.rideon = rideon;
    }

    @Override
    public String toString() {
        return "Activity {\n " +
                "image='" + image + '\'' +
                "\n date='" + date + '\'' +
                "\n rideon='" + rideon + '\'' +
                "\n name='" + name + '\'' +
                "\n distance='" + distance + '\'' +
                "\n time='" + time + '\'' +
                "\n calories='" + calories + '\'' +
                "\n}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
