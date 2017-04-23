package com.employment.model.admin.bean;

import java.io.Serializable;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by roy on 2017/4/15.
 */

public class Department extends RealmObject implements Serializable{

    /**
     * did : 2
     * depName : 网络工程
     * description : 好专业
     */

    @PrimaryKey
    private int did;
    private String depName;
    private String description;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "did=" + did +
                ", depName='" + depName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
