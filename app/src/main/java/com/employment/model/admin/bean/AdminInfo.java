package com.employment.model.admin.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by roy on 2017/4/8.
 */

public class AdminInfo extends RealmObject{


    /**
     * uid : 1
     * uname : 123456
     * urname : 管理员
     * upwd : e10adc3949ba59abbe56e057f20f883e
     * uface : null
     * uemail : 479592095@qq.com
     * uphone : 15839665365
     * urank : 1
     * ustate : 1
     * cmEmpsByUid : null
     */

    @PrimaryKey
    private int uid;
    private String uname;
    private String urname;
    private String upwd;
    private String uface;
    private String uemail;
    private String uphone;
    private int urank;
    private int ustate;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUrname() {
        return urname;
    }

    public void setUrname(String urname) {
        this.urname = urname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUface() {
        return uface;
    }

    public void setUface(String uface) {
        this.uface = uface;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public int getUrank() {
        return urank;
    }

    public void setUrank(int urank) {
        this.urank = urank;
    }

    public int getUstate() {
        return ustate;
    }

    public void setUstate(int ustate) {
        this.ustate = ustate;
    }

}
