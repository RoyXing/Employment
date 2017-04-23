package com.employment.http;

import com.employment.http.bean.ResponseBean;
import com.employment.model.admin.bean.AdminInfo;
import com.employment.model.admin.bean.Department;
import com.employment.model.company.bean.CompanyInfo;
import com.employment.http.api.AdminApi;
import com.employment.http.api.CompanyApi;
import com.employment.http.api.StudentApi;
import com.employment.http.bean.WelcomeBean;
import com.employment.model.company.bean.Interview;
import com.employment.model.student.bean.Employment;
import com.employment.model.student.bean.Note;
import com.employment.model.student.bean.Recruit;
import com.employment.model.student.bean.Resume;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.bean.UnEmployment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by roy on 2017/4/7.
 */

public class RetrofitHelper {

    private AdminApi adminApi;
    private CompanyApi companyApi;
    private StudentApi studentApi;

    public RetrofitHelper(AdminApi adminApi, CompanyApi companyApi, StudentApi studentApi) {
        this.adminApi = adminApi;
        this.companyApi = companyApi;
        this.studentApi = studentApi;
    }

    public Observable<WelcomeBean> getWelcomeInfo(String res) {
        return adminApi.getWelcomeInfo(res);
    }

    public Observable<AdminInfo> getAdminInfo(HashMap<String, String> map) {
        return adminApi.adminLoginFetchInfo(map);
    }


    public Observable<CompanyInfo> getACompanyInfo(HashMap<String, String> map) {
        return companyApi.companyLoginFetchInfo(map);
    }


    public Observable<StudentInfo> getStudentInfo(HashMap<String, String> map) {
        return studentApi.studentLoginFetchInfo(map);
    }

    public Observable<List<Recruit>> findRecruits(String type) {
        return studentApi.findRecruits(type);
    }

    public Observable<ResponseBean> applyResume(HashMap<String, String> map) {
        return studentApi.applyResume(map);
    }

    public Observable<List<Resume>> getResumeInfo(String studentId) {
        return studentApi.getResumeInfo(studentId);
    }

    public Observable<List<Note>> getAllNote(String studentId) {
        return studentApi.getAllNotes(studentId);
    }

    public Observable<ResponseBean> deleteNote(String noteId) {
        return studentApi.deleteNote(noteId);
    }

    public Observable<ResponseBean> updateNote(HashMap<String, String> map) {
        return studentApi.updateNote(map);
    }

    public Observable<ResponseBean> addNote(HashMap<String, String> map) {
        return studentApi.addNote(map);
    }

    public Observable<Employment> getEmploymentInfo(HashMap<String, String> map) {
        return studentApi.getEmploymentInfo(map);
    }

    public Observable<UnEmployment> getUnEmploymentInfo(HashMap<String, String> map) {
        return studentApi.getUnEmploymentInfo(map);
    }

    public Observable<ResponseBean> commitEmploymentInfo(HashMap<String, String> map) {
        return studentApi.commitEmploymentInfo(map);
    }

    public Observable<ResponseBean> commitStudentInfo(HashMap<String, String> map) {
        return studentApi.commitStudentInfo(map);
    }

    public Observable<List<Department>> getAllDepartment() {
        return companyApi.getAllDepartment();
    }

    public Observable<ResponseBean> commitCompanyInfo(HashMap<String, String> map) {
        return companyApi.commitCompanyInfo(map);
    }

    public Observable<List<Interview>> getAllInterview(String companyId) {
        return companyApi.getAllInterview(companyId);
    }

    public Observable<ResponseBean> publishRecruit(HashMap<String, Object> map) {
        return companyApi.publishRecruit(map);
    }

    public Observable<List<Recruit>> selectRecruitment(String type) {
        return adminApi.adminSelectRecruitment(type);
    }

    public Observable<ResponseBean> RecruitmentState(Map<String ,String>map) {
        return adminApi.RecruitmentState(map);
    }
}
