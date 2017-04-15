package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

/**
 * Created by roy on 2017/4/7.
 */

public interface MainContract {

    interface View extends BaseView {
        void setHeader(Object object,int type);
    }

    interface Presenter extends BasePresenter<View> {

        void getUserInfo(int type);
    }
}
