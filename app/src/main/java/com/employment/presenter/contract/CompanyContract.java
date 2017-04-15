package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.student.bean.Resume;

import java.util.List;

/**
 * Created by roy on 2017/4/9.
 */

public interface CompanyContract {

    interface View extends BaseView {
        void showContent(List<Resume> list);
    }

    interface Presenter extends BasePresenter<View> {

        void getResumeInfo();

        void getMoreResumeInfo();
    }
}
