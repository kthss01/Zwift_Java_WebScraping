package com.kay.model.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityRide extends Activity {
    private String elevation;

    public ActivityRide(String url, String image, String date, String rideon, String name, String distance, String time, String calories, String elevation) {
        super(url, image, date, rideon, name, distance, time, calories);
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "ActivityRide {\n " +
                "elevation='" + elevation + '\'' +
                "}\n" + super.toString();
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
}
