package com.kay.model.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityRun extends Activity {
    private String pace;

    public ActivityRun(String image, String date, String rideon, String name, String distance, String time, String calories, String pace) {
        super(image, date, rideon, name, distance, time, calories);
        this.pace = pace;
    }

    @Override
    public String toString() {
        return "ActivityRun {\n " +
                "pace='" + pace + '\'' +
                "}\n" + super.toString();
    }



    public String getPace() {
        return pace;
    }

    public void setPace(String pace) {
        this.pace = pace;
    }
}
