package com.employment.model.student.bean;

/**
 * Created by roy on 2017/4/15.
 */

public class Resume {

    /**
     * iid : 1
     * iaddress : 苏州创业园
     * itype : 直聘
     * itime : 1492185600000
     * isuccess : 0
     * istate : 0
     * cmStudentBySid : null
     * cmRecruitByRid : {"rid":1,"rsex":null,"rsalary":5000,"rstart":1490976000000,"rend":1492531200000,"rnum":5,"rinfo":"【高级Android 开发工程师】 \n岗位职责： \n1.参与公司产品研发； \n2.负责产品在Android上移动应用的分析、设计、编码、单元测试和集成测试； \n3.集成并优化第三方软件模块。 \n职位要求： \n1.具备2年以上Android系统开发经验，完成过2个以上Android手机应用并成功上线； \n2.具备JAVA开发经验，熟悉Android手机软件架构； \n3.熟悉iOS端开发者优先； \n4.熟悉Android手机硬件结构者优先； \n5.思路清晰，思维敏捷，具备快速学习能力和良好的英文技术资料阅读能力。","rstate":0,"rtype":0,"cmIntersByRid":null,"cmJobByJid":{"jid":1,"jname":"Android开发工程师","jtype":true,"jstate":0,"jinfo":"好工作","cmEmpsByJid":null,"cmRecruitsByJid":null,"cmUnempsByJid":null},"cmCompanyByCid":{"cid":1,"cname":"华为","cpassword":null,"cface":"http://imgsrc.baidu.com/forum/pic/item/b03533fa828ba61ea84d4c8f4134970a314e59ea.jpg","chr":"王小明","cphone":"13102215678","cemail":"xiaom@huawei.com","cinfo":"华为目前已成长为年销售规模超3900亿人民币的世界500强公司,面向未来,华为将与众多伙伴开放合作,努力共建全联接世界 ","cmark":"好好好，非常好","caddress":"坂田区","ctime":1491908464000,"cstate":0,"ctype":null,"cmAreaByAid":null,"cmRecruitsByCid":null},"cmAreaByAid":null}
     * cmAreaByAid : null
     * isuccleave : null
     */

    private int iid;
    private String iaddress;
    private String itype;
    private String itime;
    private int isuccess;
    private int istate;
    private Object cmStudentBySid;
    private Recruit cmRecruitByRid;
    private Object cmAreaByAid;
    private Object isuccleave;

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getIaddress() {
        return iaddress;
    }

    public void setIaddress(String iaddress) {
        this.iaddress = iaddress;
    }

    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype;
    }

    public String getItime() {
        return itime;
    }

    public void setItime(String itime) {
        this.itime = itime;
    }

    public int getIsuccess() {
        return isuccess;
    }

    public void setIsuccess(int isuccess) {
        this.isuccess = isuccess;
    }

    public int getIstate() {
        return istate;
    }

    public void setIstate(int istate) {
        this.istate = istate;
    }

    public Object getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(Object cmStudentBySid) {
        this.cmStudentBySid = cmStudentBySid;
    }

    public Recruit getCmRecruitByRid() {
        return cmRecruitByRid;
    }

    public void setCmRecruitByRid(Recruit cmRecruitByRid) {
        this.cmRecruitByRid = cmRecruitByRid;
    }

    public Object getCmAreaByAid() {
        return cmAreaByAid;
    }

    public void setCmAreaByAid(Object cmAreaByAid) {
        this.cmAreaByAid = cmAreaByAid;
    }

    public Object getIsuccleave() {
        return isuccleave;
    }

    public void setIsuccleave(Object isuccleave) {
        this.isuccleave = isuccleave;
    }
}
