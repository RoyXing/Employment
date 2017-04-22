package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.admin.bean.Department;

import java.util.List;

/**
 * Created by roy on 2017/4/9.
 */

public interface CheckContract {

    interface View extends BaseView {

        void showAllDepartment(List<Department> departments);
    }

    interface Presenter extends BasePresenter<View> {
        void getAllDepartment();
    }
}
