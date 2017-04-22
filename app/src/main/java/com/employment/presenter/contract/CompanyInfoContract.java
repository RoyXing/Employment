package com.employment.presenter.contract;

import android.widget.LinearLayout;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.company.bean.CompanyInfo;

import java.util.Date;

/**
 * Created by roy on 2017/4/9.
 */

public interface CompanyInfoContract {

    interface View extends BaseView {

        void showContent(CompanyInfo companyInfo);

        void showModifyInfo(String newInfo,String type);

        void showBuildTime(String time);
    }

    interface Presenter extends BasePresenter<View> {

        void getCompanyInfo();

        void setTime(long date);

        void modifyInfo(LinearLayout view, String info, String type);

        void commitCompanyInfo();
    }
}
