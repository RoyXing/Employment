package com.employment.model.student.event;

/**
 * Created by roy on 2017/4/16.
 */

public class NoteEvent {

    private int type;


    public NoteEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
