package com.employment.model.admin.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.HomePresenter;
import com.employment.presenter.contract.HomeContract;

/**
 * Created by roy on 2017/3/28.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_admin_home;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showError(String msg) {

    }
}
