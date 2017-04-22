package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

/**
 * Created by roy on 2017/4/17.
 */

public interface PersonalModifyContract {

    interface View extends BaseView {

        void setCommentSuccess();

    }

    interface Presenter extends BasePresenter<View> {

        void setSelfComment(String comment);

        void commitEmploymentInfo(String status, String position, String salary, String time);

        void commitUnEmployment(String status, String position, String salary);
    }
}
