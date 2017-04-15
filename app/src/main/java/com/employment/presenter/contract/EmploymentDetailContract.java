package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.student.bean.Resume;

import java.util.List;

/**
 * Created by roy on 2017/4/15.
 */

public interface EmploymentDetailContract {

    interface View extends BaseView {

        void applyOk();

        void isCanApply( List<Resume> resumes);
    }

    interface Presenter extends BasePresenter<View> {

        void applyResume(int recruitId, int areaId);

        void isApply();
    }
}
