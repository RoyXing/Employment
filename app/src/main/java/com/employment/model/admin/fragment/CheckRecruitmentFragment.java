package com.employment.model.admin.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.CheckRecruitmentPresenter;
import com.employment.presenter.contract.CheckRecruitmentContract;

/**
 * Created by roy on 2017/3/28.
 */

public class CheckRecruitmentFragment extends BaseFragment<CheckRecruitmentPresenter> implements CheckRecruitmentContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_recruitment;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showError(String msg) {

    }
}
