package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.student.bean.Recruit;
import com.employment.model.student.bean.Resume;

import java.util.List;
import java.util.Map;

/**
 * Created by 陈学勤 on 2017/4/23.
 */

public interface CheckEmploymentDetailContract {
    interface View extends BaseView {


    }

    interface Presenter extends BasePresenter<CheckEmploymentDetailContract.View> {
        void pass(Map<String,String> map, final String state);

    }
}
