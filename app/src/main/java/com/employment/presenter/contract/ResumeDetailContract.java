package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

/**
 * Created by roy on 2017/4/23.
 */

public interface ResumeDetailContract {

    interface View extends BaseView {
        void commitSuccess(String type);
    }

    interface Presenter extends BasePresenter<View> {
        void commitResumeStatus(String id, String type);
    }

}
