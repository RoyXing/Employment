package com.employment.model.student.event;

/**
 * Created by roy on 2017/4/16.
 */

public class ResumeEvent {

    private int type;

    public ResumeEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
