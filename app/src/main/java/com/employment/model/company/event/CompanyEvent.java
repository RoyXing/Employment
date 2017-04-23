package com.employment.model.company.event;

/**
 * Created by roy on 2017/4/23.
 */

public class CompanyEvent {

    private String type;

    public CompanyEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
