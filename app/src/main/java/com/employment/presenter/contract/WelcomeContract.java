package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.http.bean.WelcomeBean;

/**
 * Created by roy on 2017/4/7.
 */

public interface WelcomeContract {

    interface View extends BaseView {
        void showContent(WelcomeBean welcomeBean);

        void jumpWhichPage();
    }

    interface Presenter extends BasePresenter<View> {

        void getWelcomeImage();
    }
}
