package com.employment.http.api;

import com.employment.http.bean.ResponseBean;
import com.employment.model.student.bean.Employment;
import com.employment.model.student.bean.Note;
import com.employment.model.student.bean.Recruit;
import com.employment.model.student.bean.Resume;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.bean.UnEmployment;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by roy on 2017/4/7.
 */

public interface StudentApi {

    @POST("login/login")
    Observable<StudentInfo> studentLoginFetchInfo(@QueryMap HashMap<String, String> map);

    @GET("recruit/findRecruits")
    Observable<List<Recruit>> findRecruits(@Query("type") String type);

    @POST("student/deliverResume")
    Observable<ResponseBean> applyResume(@QueryMap HashMap<String, String> map);

    @GET("student/getStudentRecruit")
    Observable<List<Resume>> getResumeInfo(@Query("studentId") String studentId);

    @GET("note/findByStudentId")
    Observable<List<Note>> getAllNotes(@Query("studentId") String studentId);

    @GET("note/delete")
    Observable<ResponseBean> deleteNote(@Query("noteId") String noteId);

    @POST("note/update")
    Observable<ResponseBean> updateNote(@QueryMap HashMap<String, String> map);

    @POST("note/add")
    Observable<ResponseBean> addNote(@QueryMap HashMap<String, String> map);

    @GET("student/getEmpState")
    Observable<Employment> getEmploymentInfo(@QueryMap HashMap<String, String> map);

    @GET("student/getEmpState")
    Observable<UnEmployment> getUnEmploymentInfo(@QueryMap HashMap<String, String> map);

    @POST("student/changeGraduateInfo")
    Observable<ResponseBean> commitEmploymentInfo(@QueryMap HashMap<String, String> map);

    @POST("student/updateStudentInfo")
    Observable<ResponseBean> commitStudentInfo(@QueryMap HashMap<String, String> map);

    @POST("student/updateUnempInfo")
    Observable<ResponseBean> commitUnEmploymentInfo(@QueryMap HashMap<String, String> map);
}
