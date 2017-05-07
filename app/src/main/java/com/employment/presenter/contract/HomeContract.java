package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.admin.bean.Department;
import com.employment.model.student.bean.Employment;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.bean.UnEmployment;

import java.util.List;

/**
 * Created by roy on 2017/4/9.
 */

public interface HomeContract {

    interface View extends BaseView {

        void showAllStudent(List<StudentInfo> infoList);

        void showAllDepartmentInfo(List<Department> departments);

        void showUnEmploymentInfo(List<UnEmployment> unEmploymentList);

        void showAllUnEmploymentByDepartment(List<UnEmployment> unEmploymentList, String name);

    }

    interface Presenter extends BasePresenter<View> {

        void getAllStudentInfo();

        void getAllDepartmentInfo();

        void getUnEmploymentByDepartment(String id, String name);

        void getAllUnEmploymentInfo();
    }

}
