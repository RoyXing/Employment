package com.employment.model.student.bean;

import com.employment.model.company.bean.CompanyInfo;
import com.employment.model.company.bean.Job;

import java.io.Serializable;

/**
 * Created by roy on 2017/4/15.
 */

public class Recruit implements Serializable{


    /**
     * rid : 1
     * rsex : null
     * rsalary : 5000
     * rstart : 1490976000000
     * rend : 1492531200000
     * rnum : 5
     * rinfo : 软件工程师
     * rstate : 0
     * rtype : 1
     * cmIntersByRid : null
     * cmJobByJid : {"jid":1,"jname":"Android开发工程师","jtype":true,"jstate":0,"jinfo":"好工作","cmEmpsByJid":null,"cmRecruitsByJid":null,"cmUnempsByJid":null}
     * cmCompanyByCid : {"cid":1,"cname":"华为","cpassword":null,"cface":null,"chr":"王小明","cphone":"13102215678","cemail":"xiaom@huawei.com","cinfo":"华为目前已成长为年销售规模超3900亿人民币的世界500强公司,面向未来,华为将与众多伙伴开放合作,努力共建全联接世界 ","cmark":"好好好，非常好","caddress":"坂田区","ctime":1491908464000,"cstate":0,"ctype":null,"cmAreaByAid":null,"cmRecruitsByCid":null}
     * cmAreaByAid : null
     */

    private int rid;
    private Object rsex;
    private int rsalary;
    private String rstart;
    private String rend;
    private int rnum;
    private String rinfo;
    private int rstate;
    private int rtype;
    private String rjobName;
    private Object cmIntersByRid;
    private Job cmJobByJid;
    private CompanyInfo cmCompanyByCid;
    private Object cmAreaByAid;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Object getRsex() {
        return rsex;
    }

    public void setRsex(Object rsex) {
        this.rsex = rsex;
    }

    public int getRsalary() {
        return rsalary;
    }

    public void setRsalary(int rsalary) {
        this.rsalary = rsalary;
    }

    public String getRstart() {
        return rstart;
    }

    public void setRstart(String rstart) {
        this.rstart = rstart;
    }

    public String getRend() {
        return rend;
    }

    public void setRend(String rend) {
        this.rend = rend;
    }

    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    public String getRinfo() {
        return rinfo;
    }

    public void setRinfo(String rinfo) {
        this.rinfo = rinfo;
    }

    public int getRstate() {
        return rstate;
    }

    public void setRstate(int rstate) {
        this.rstate = rstate;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public Object getCmIntersByRid() {
        return cmIntersByRid;
    }

    public void setCmIntersByRid(Object cmIntersByRid) {
        this.cmIntersByRid = cmIntersByRid;
    }

    public Job getCmJobByJid() {
        return cmJobByJid;
    }

    public void setCmJobByJid(Job cmJobByJid) {
        this.cmJobByJid = cmJobByJid;
    }

    public CompanyInfo getCmCompanyByCid() {
        return cmCompanyByCid;
    }

    public void setCmCompanyByCid(CompanyInfo cmCompanyByCid) {
        this.cmCompanyByCid = cmCompanyByCid;
    }

    public Object getCmAreaByAid() {
        return cmAreaByAid;
    }

    public void setCmAreaByAid(Object cmAreaByAid) {
        this.cmAreaByAid = cmAreaByAid;
    }

    public String getRjobName() {
        return rjobName;
    }

    public void setRjobName(String rjobName) {
        this.rjobName = rjobName;
    }
}
