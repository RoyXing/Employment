package com.employment.model.admin.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.CheckPostPresenter;
import com.employment.presenter.contract.CheckPostContract;

/**
 * Created by roy on 2017/3/28.
 */

public class CheckPostFragment extends BaseFragment<CheckPostPresenter> implements CheckPostContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_post;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showError(String msg) {

    }
}
