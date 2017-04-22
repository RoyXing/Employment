package com.employment.model.student.event;

/**
 * Created by roy on 2017/4/19.
 */

public class EmploymentEvent {

    private String type;

    public EmploymentEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
