package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

/**
 * Created by roy on 2017/4/9.
 */

public interface CheckPostContract {

    interface View extends BaseView{}

    interface Presenter extends BasePresenter<View>{}
}
