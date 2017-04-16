package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.student.bean.StudentInfo;

/**
 * Created by roy on 2017/4/9.
 */

public interface PersonalContract {

    interface View extends BaseView{
        void showContent(StudentInfo studentInfo);
    }

    interface Presenter extends BasePresenter<View>{
        void getStudentInfo();
    }
}
