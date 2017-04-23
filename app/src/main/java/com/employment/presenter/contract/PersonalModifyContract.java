package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

import java.util.Date;

/**
 * Created by roy on 2017/4/17.
 */

public interface PersonalModifyContract {

    interface View extends BaseView {

        void setCommentSuccess();

        void showStayDate(String date);

    }

    interface Presenter extends BasePresenter<View> {

        void setSelfComment(String comment);

        void commitEmploymentInfo(String status, String position, String salary, String time);

        void commitUnEmployment(String status, String position, String salary);

        void chooseStayTime(Date date);
    }
}
