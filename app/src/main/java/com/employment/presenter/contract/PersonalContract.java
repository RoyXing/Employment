package com.employment.presenter.contract;

import android.widget.LinearLayout;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.student.bean.Employment;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.bean.UnEmployment;

import java.util.Date;

/**
 * Created by roy on 2017/4/9.
 */

public interface PersonalContract {

    interface View extends BaseView {

        void showContent(StudentInfo studentInfo);

        void showEmploymentInfo(Employment employment);

        void showUnEmploymentInfo(UnEmployment unEmployment);

        void showBirthday(String date);

        void showNewPhone(String newPhone);

        void showNewEmail(String newEmail);
    }

    interface Presenter extends BasePresenter<View> {

        void getStudentInfo();

        void getEmployInfo();

        void setBirthday(Date date);

        void setPhone(LinearLayout view, String phone);

        void setEmail(LinearLayout view,String email);
    }
}
