package com.employment.model.company.bean;

import com.employment.model.student.bean.Recruit;
import com.employment.model.student.bean.StudentInfo;

/**
 * Created by roy on 2017/4/22.
 */

public class Interview {


    /**
     * iid : 26
     * iaddress : null
     * itype : 0
     * itime : 2017-04-17
     * isuccess : 0
     * istate : null
     * cmStudentBySid : {"sid":2,"sno":"121007130","sname":"小红","spassword":"e10adc3949ba59abbe56e057f20f883e","sface":"http://d.5857.com/heseneiyi_15812/007.jpg","ssex":true,"sbirth":"1993-02-01","spro":"计算机科学与技术","sgrade":2013,"sclass":4,"sphone":"15839665365","semail":"479592095@qq.com","scode":"37292819960127072X","smark":3,"sassess":"好","sstate":4,"sdetail":"本人特别强 说出来你可能不信\n\n其实我也不太确定","cmEmpsBySid":null,"cmGradesBySid":null,"cmIntersBySid":null,"cmUnempsBySid":null,"department":{"did":2,"depName":"网络工程","description":"好专业"}}
     * cmRecruitByRid : {"rid":1,"rsex":null,"rsalary":5000,"rstart":"2017-04-01","rend":"2017-04-19","rnum":5,"rinfo":"【高级Android 开发工程师】 \n岗位职责： \n1.参与公司产品研发； \n2.负责产品在Android上移动应用的分析、设计、编码、单元测试和集成测试； \n3.集成并优化第三方软件模块。 \n职位要求： \n1.具备2年以上Android系统开发经验，完成过2个以上Android手机应用并成功上线； \n2.具备JAVA开发经验，熟悉Android手机软件架构； \n3.熟悉iOS端开发者优先； \n4.熟悉Android手机硬件结构者优先； \n5.思路清晰，思维敏捷，具备快速学习能力和良好的英文技术资料阅读能力。","rstate":0,"rtype":0,"rjobName":"android","cmIntersByRid":null,"cmJobByJid":{"jid":1,"jname":"Android开发工程师","jtype":true,"jstate":0,"jinfo":"好工作","cmEmpsByJid":null,"cmRecruitsByJid":null,"cmUnempsByJid":null},"cmCompanyByCid":{"cid":1,"cname":"华为","cpassword":"e10adc3949ba59abbe56e057f20f883e","cface":"http://imgsrc.baidu.com/forum/pic/item/b03533fa828ba61ea84d4c8f4134970a314e59ea.jpg","chr":"王小明","cphone":"15839665365","cemail":"xiaom@huawei.com","cinfo":"华为目前已成长为年销售规模超3900亿人民币的世界500强公司,面向未来,华为将与众多伙伴开放合作,努力共建全联接世界 厉害的很","cmark":"好好好，非常好","caddress":"北京人民广场","ctime":"2017-05-31","cstate":0,"ctype":null,"cmAreaByAid":null,"cmRecruitsByCid":null},"cmAreaByAid":null}
     * cmAreaByAid : null
     * isuccleave : null
     */

    private int iid;
    private String iaddress;
    private String itype;
    private String itime;
    private int isuccess;
    private Object istate;
    private StudentInfo cmStudentBySid;
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

    public Object getIstate() {
        return istate;
    }

    public void setIstate(Object istate) {
        this.istate = istate;
    }

    public StudentInfo getCmStudentBySid() {
        return cmStudentBySid;
    }

    public void setCmStudentBySid(StudentInfo cmStudentBySid) {
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
