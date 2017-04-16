package com.employment.model.student.bean;

import java.io.Serializable;

/**
 * Created by roy on 2017/4/16.
 */

public class Note implements Serializable{

    /**
     * nid : 1
     * student : {"sid":2,"sno":"121007130","sname":"小红","spassword":"e10adc3949ba59abbe56e057f20f883e","sface":"http://d.5857.com/heseneiyi_15812/007.jpg","ssex":true,"sbirth":"1996-01-27","spro":"计算机科学与技术","sgrade":2013,"sclass":4,"sphone":"13583213333","semail":"123@126.com","scode":"37292819960127072X","smark":3,"sassess":"好","sstate":0,"sdetail":"好","cmEmpsBySid":null,"cmGradesBySid":null,"cmIntersBySid":null,"cmUnempsBySid":null,"department":{"did":2,"depName":"网络工程","description":"好专业"}}
     * content : 一个原因是上海（虹桥、浦东）和无锡（硕放）都有机场，苏州离两地都不算太远，因此从避免重复投资角度就没有建设自己的机场。据说当时苏州也有自建机场的意向，但是项目审批时被否决了。之后苏州就参股无锡硕放机场，现在该机场已经更名为苏南机场，从名字也能反映该机场的定位已经涵盖了苏南地区而不是仅仅无锡。
     * <p>
     * 至于你说的机场路，就是现在的金鸡湖大道，只是改名了，并不是你说的不了了之。当时该路是通往虹桥机场的，因此就命名为机场路。之后因为高速公路的建设，苏州到上海机场已经更多由沪宁高速公路(G2/G42)来承担，机场路也就失去了原来命名的初衷，因此更名为金鸡湖大道。
     * <p>
     * 作者：threewater
     * 链接：https://www.zhihu.com/question/20356044/answer/14985341
     * 来源：知乎
     * 著作权归作者所有，转载请联系作者获得授权。
     * createAt : null
     * updateAt : null
     */

    private int nid;
    private StudentInfo student;
    private String content;
    private long createAt;
    private long updateAt;
    private String title;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public Object getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public StudentInfo getStudent() {
        return student;
    }

    public void setStudent(StudentInfo student) {
        this.student = student;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
