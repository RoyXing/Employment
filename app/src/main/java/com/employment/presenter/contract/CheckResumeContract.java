package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.company.bean.Interview;

import java.util.List;

/**
 * Created by roy on 2017/4/9.
 */

public interface CheckResumeContract {

    interface View extends BaseView {

        void showContent(List<Interview> interviews);
    }

    interface Presenter extends BasePresenter<View> {

        void getAllInterview();
    }
}
