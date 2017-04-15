package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

/**
 * Created by roy on 2017/4/8.
 */

public interface LoginContract {

    interface View extends BaseView{

        void loginSuccess();

        void loginFailure();
    }

    interface Presenter extends BasePresenter<View>{

        void login(String userName,String passWord,String type);
    }
}
