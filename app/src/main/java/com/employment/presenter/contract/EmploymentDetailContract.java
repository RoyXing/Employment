package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

/**
 * Created by roy on 2017/4/15.
 */

public interface EmploymentDetailContract {

    interface View extends BaseView {
        void applyOk();
    }

    interface Presenter extends BasePresenter<View> {

        void applyResume(int recruitId,int areaId);
    }
}
