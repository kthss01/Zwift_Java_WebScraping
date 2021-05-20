package com.kay.model.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Activity {
    private LocalDate date;
    private String name;
    private float distance;
    private LocalTime time;
    private int calories;
}
