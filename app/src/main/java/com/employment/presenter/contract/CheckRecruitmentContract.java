package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.student.bean.Recruit;

import java.util.List;

/**
 * Created by roy on 2017/4/9.
 */

public interface CheckRecruitmentContract {

    interface View extends BaseView {

        void showContent(List<Recruit> recruits);

        void showMoreContent();

    }

    interface Presenter extends BasePresenter<View> {

        void loadEmploymentInfo(String type);

        void loadEmployMoreInfo(String type);
    }


}
