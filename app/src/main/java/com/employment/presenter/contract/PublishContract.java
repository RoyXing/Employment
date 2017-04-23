package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by roy on 2017/4/9.
 */

public interface PublishContract {

    interface View extends BaseView {

        void publishSuccess();

        void setTime(String time,int type);
    }

    interface Presenter extends BasePresenter<View> {

        void publishRecruit(HashMap<String, Object> map);

        void chooseTime(Date date, int type);
    }
}
