package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.student.bean.Employment;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.bean.UnEmployment;

/**
 * Created by roy on 2017/4/9.
 */

public interface PersonalContract {

    interface View extends BaseView {

        void showContent(StudentInfo studentInfo);

        void showEmploymentInfo(Employment employment);

        void showUnEmploymentInfo(UnEmployment unEmployment);
    }

    interface Presenter extends BasePresenter<View> {

        void getStudentInfo();

        void getEmployInfo();
    }
}
