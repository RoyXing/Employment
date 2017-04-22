package com.employment.model.student.bean;

/**
 * Created by roy on 2017/4/16.
 */

public class Employment {

    /**
     * eid : 1
     * etime : 1420128000000
     * esalary : 5000
     * einfo : null
     * estate : 0
     * ewq : false
     * eleave : 2016-10-12
     * ereason : 记录卡撒旦发就立刻
     * ejobName : null
     * cmStudentBySid : null
     * cmJobByJid : null
     * cmUserByUid : null
     */

    private int eid;
    private long etime;
    private int esalary;
    private String einfo;
    private int estate;
    private boolean ewq;
    private String eleave;
    private String ereason;
    private String ejobName;
    private Object cmStudentBySid;
    private Object cmJobByJid;
    private Object cmUserByUid;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public long getEtime() {
        return etime;
    }

    public void setEtime(long etime) {
        this.etime = etime;
    }

    public int getEsalary() {
        return esalary;
    }

    public void setEsalary(int esalary) {
        this.esalary = esalary;
    }

    public String getEinfo() {
        return einfo;
    }

    public void setEinfo(String einfo) {
        this.einfo = einfo;
    }

    public int getEstate() {
        return estate;
    }

    public void setEstate(int estate) {
        this.estate = estate;
    }

    public boolean isEwq() {
        return ewq;
    }

    public void setEwq(boolean ewq) {
        this.ewq = ewq;
    }

    public String getEleave() {
        return eleave;
    }

    public void setEleave(String eleave) {
        this.eleave = eleave;
    }

    public String getEreason() {
        return ereason;
    }

    public void setEreason(String ereason) {
        this.ereason = ereason;
    }

    public String getEjobName() {
        return ejobName;
    }

    public void setEjobName(String ejobName) {
        this.ejobName = ejobName;
    }

    public Object getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(Object cmStudentBySid) {
        this.cmStudentBySid = cmStudentBySid;
    }

    public Object getCmJobByJid() {
        return cmJobByJid;
    }

    public void setCmJobByJid(Object cmJobByJid) {
        this.cmJobByJid = cmJobByJid;
    }

    public Object getCmUserByUid() {
        return cmUserByUid;
    }

    public void setCmUserByUid(Object cmUserByUid) {
        this.cmUserByUid = cmUserByUid;
    }
}
