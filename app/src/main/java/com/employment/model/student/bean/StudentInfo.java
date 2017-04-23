package com.employment.model.student.bean;

import com.employment.model.admin.bean.Department;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by roy on 2017/4/8.
 */

public class StudentInfo extends RealmObject implements Serializable{

    /**
     * sid : 2
     * sno : 121007130
     * sname : 小红
     * spassword : e10adc3949ba59abbe56e057f20f883e
     * sface : http://img.taopic.com/uploads/allimg/140806/235020-140P60H10661.jpg
     * ssex : true
     * sbirth : 1996-01-27
     * spro : 计算机科学与技术
     * sgrade : 2013
     * sclass : 4
     * sphone : 13583213333
     * semail : 123@126.com
     * scode : 37292819960127072X
     * smark : 10
     * sassess : 好
     * sstate : 0
     * sdetail : 好
     */

    @PrimaryKey
    private int sid;
    private String sno;
    private String sname;
    private String spassword;
    private String sface;
    private boolean ssex;
    private Date sbirth;
    private String spro;
    private int sgrade;
    private int sclass;
    private String sphone;
    private String semail;
    private String scode;
    private int smark;
    private String sassess;
    private int sstate;
    private String sdetail;
    private Department department;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getSface() {
        return sface;
    }

    public void setSface(String sface) {
        this.sface = sface;
    }

    public boolean isSsex() {
        return ssex;
    }

    public void setSsex(boolean ssex) {
        this.ssex = ssex;
    }

    public Date getSbirth() {
        return sbirth;
    }

    public void setSbirth(Date sbirth) {
        this.sbirth = sbirth;
    }

    public String getSpro() {
        return spro;
    }

    public void setSpro(String spro) {
        this.spro = spro;
    }

    public int getSgrade() {
        return sgrade;
    }

    public void setSgrade(int sgrade) {
        this.sgrade = sgrade;
    }

    public int getSclass() {
        return sclass;
    }

    public void setSclass(int sclass) {
        this.sclass = sclass;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public int getSmark() {
        return smark;
    }

    public void setSmark(int smark) {
        this.smark = smark;
    }

    public String getSassess() {
        return sassess;
    }

    public void setSassess(String sassess) {
        this.sassess = sassess;
    }

    public int getSstate() {
        return sstate;
    }

    public void setSstate(int sstate) {
        this.sstate = sstate;
    }

    public String getSdetail() {
        return sdetail;
    }

    public void setSdetail(String sdetail) {
        this.sdetail = sdetail;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "sid=" + sid +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", spassword='" + spassword + '\'' +
                ", sface='" + sface + '\'' +
                ", ssex=" + ssex +
                ", sbirth='" + sbirth + '\'' +
                ", spro='" + spro + '\'' +
                ", sgrade=" + sgrade +
                ", sclass=" + sclass +
                ", sphone='" + sphone + '\'' +
                ", semail='" + semail + '\'' +
                ", scode='" + scode + '\'' +
                ", smark=" + smark +
                ", sassess='" + sassess + '\'' +
                ", sstate=" + sstate +
                ", sdetail='" + sdetail + '\'' +
                ", department=" + department +
                '}';
    }
}
