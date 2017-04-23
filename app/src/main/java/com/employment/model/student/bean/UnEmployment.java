package com.employment.model.student.bean;

import com.employment.model.company.bean.Job;

/**
 * Created by roy on 2017/4/16.
 */

public class UnEmployment {

    /**
     * ueid : 1
     * uesalary : 5000
     * uetime : 1494518400000
     * ueschool : 山东大学
     * uemajor : 计算机科学与技术
     * uesuccess : 0
     * uestate : 0
     * cmStudentBySid : null
     * cmDirectionByDid : null
     * cmJobByJid : null
     */

    private int ueid;
    private int uesalary;
    private String uetime;
    private String ueschool;
    private String uemajor;
    private int uesuccess;
    private int uestate;
    private String ujobName;
    private Object cmStudentBySid;
    private Object cmDirectionByDid;
    private Job cmJobByJid;

    public int getUeid() {
        return ueid;
    }

    public void setUeid(int ueid) {
        this.ueid = ueid;
    }

    public int getUesalary() {
        return uesalary;
    }

    public void setUesalary(int uesalary) {
        this.uesalary = uesalary;
    }

    public String getUetime() {
        return uetime;
    }

    public void setUetime(String uetime) {
        this.uetime = uetime;
    }

    public String getUeschool() {
        return ueschool;
    }

    public void setUeschool(String ueschool) {
        this.ueschool = ueschool;
    }

    public String getUemajor() {
        return uemajor;
    }

    public void setUemajor(String uemajor) {
        this.uemajor = uemajor;
    }

    public int getUesuccess() {
        return uesuccess;
    }

    public void setUesuccess(int uesuccess) {
        this.uesuccess = uesuccess;
    }

    public int getUestate() {
        return uestate;
    }

    public void setUestate(int uestate) {
        this.uestate = uestate;
    }

    public Object getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(Object cmStudentBySid) {
        this.cmStudentBySid = cmStudentBySid;
    }

    public Object getCmDirectionByDid() {
        return cmDirectionByDid;
    }

    public void setCmDirectionByDid(Object cmDirectionByDid) {
        this.cmDirectionByDid = cmDirectionByDid;
    }

    public Job getCmJobByJid() {
        return cmJobByJid;
    }

    public void setCmJobByJid(Job cmJobByJid) {
        this.cmJobByJid = cmJobByJid;
    }

    public String getUjobName() {
        return ujobName;
    }

    public void setUjobName(String ujobName) {
        this.ujobName = ujobName;
    }
}
