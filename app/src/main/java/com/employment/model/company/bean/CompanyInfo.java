package com.employment.model.company.bean;

import java.io.Serializable;

/**
 * Created by roy on 2017/4/8.
 */

public class CompanyInfo implements Serializable{

    /**
     * cid : 1
     * cname : 华为
     * cpassword : null
     * cface : null
     * chr : 王小明
     * cphone : 13102215678
     * cemail : xiaom@huawei.com
     * cinfo : 华为目前已成长为年销售规模超3900亿人民币的世界500强公司,面向未来,华为将与众多伙伴开放合作,努力共建全联接世界
     * cmark : 好好好，非常好
     * caddress : 坂田区
     * ctime : 1491908464000
     * cstate : 0
     * ctype : null
     * cmAreaByAid : null
     * cmRecruitsByCid : null
     */

    private int cid;
    private String cname;
    private Object cpassword;
    private Object cface;
    private String chr;
    private String cphone;
    private String cemail;
    private String cinfo;
    private String cmark;
    private String caddress;
    private long ctime;
    private int cstate;
    private Object ctype;
    private Object cmAreaByAid;
    private Object cmRecruitsByCid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Object getCpassword() {
        return cpassword;
    }

    public void setCpassword(Object cpassword) {
        this.cpassword = cpassword;
    }

    public Object getCface() {
        return cface;
    }

    public void setCface(Object cface) {
        this.cface = cface;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getCinfo() {
        return cinfo;
    }

    public void setCinfo(String cinfo) {
        this.cinfo = cinfo;
    }

    public String getCmark() {
        return cmark;
    }

    public void setCmark(String cmark) {
        this.cmark = cmark;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getCstate() {
        return cstate;
    }

    public void setCstate(int cstate) {
        this.cstate = cstate;
    }

    public Object getCtype() {
        return ctype;
    }

    public void setCtype(Object ctype) {
        this.ctype = ctype;
    }

    public Object getCmAreaByAid() {
        return cmAreaByAid;
    }

    public void setCmAreaByAid(Object cmAreaByAid) {
        this.cmAreaByAid = cmAreaByAid;
    }

    public Object getCmRecruitsByCid() {
        return cmRecruitsByCid;
    }

    public void setCmRecruitsByCid(Object cmRecruitsByCid) {
        this.cmRecruitsByCid = cmRecruitsByCid;
    }
}
