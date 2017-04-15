package com.employment.model.company.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.CheckResumePresenter;
import com.employment.presenter.contract.CheckResumeContract;

/**
 * Created by roy on 2017/3/28.
 */

public class CheckResumeFragment extends BaseFragment<CheckResumePresenter> implements CheckResumeContract.View {

    public static CheckResumeFragment newInstance(){return new CheckResumeFragment();}

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_resume;
    }

    @Override
    protected void initEventAndData() {

    }
}
