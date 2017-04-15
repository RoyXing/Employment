package com.employment.model.company.bean;

import java.io.Serializable;

/**
 * Created by roy on 2017/4/15.
 */

public class Job implements Serializable{
    /**
     * jid : 1
     * jname : Android开发工程师
     * jtype : true
     * jstate : 0
     * jinfo : 好工作
     * cmEmpsByJid : null
     * cmRecruitsByJid : null
     * cmUnempsByJid : null
     */

    private int jid;
    private String jname;
    private boolean jtype;
    private int jstate;
    private String jinfo;
    private Object cmEmpsByJid;
    private Object cmRecruitsByJid;
    private Object cmUnempsByJid;

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public boolean isJtype() {
        return jtype;
    }

    public void setJtype(boolean jtype) {
        this.jtype = jtype;
    }

    public int getJstate() {
        return jstate;
    }

    public void setJstate(int jstate) {
        this.jstate = jstate;
    }

    public String getJinfo() {
        return jinfo;
    }

    public void setJinfo(String jinfo) {
        this.jinfo = jinfo;
    }

    public Object getCmEmpsByJid() {
        return cmEmpsByJid;
    }

    public void setCmEmpsByJid(Object cmEmpsByJid) {
        this.cmEmpsByJid = cmEmpsByJid;
    }

    public Object getCmRecruitsByJid() {
        return cmRecruitsByJid;
    }

    public void setCmRecruitsByJid(Object cmRecruitsByJid) {
        this.cmRecruitsByJid = cmRecruitsByJid;
    }

    public Object getCmUnempsByJid() {
        return cmUnempsByJid;
    }

    public void setCmUnempsByJid(Object cmUnempsByJid) {
        this.cmUnempsByJid = cmUnempsByJid;
    }
}
