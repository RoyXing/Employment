package com.employment.model.student.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.contract.CompanyContract;
import com.employment.presenter.CompanyPresenter;

/**
 * Created by roy on 2017/3/28.
 */

public class CompanyFragment extends BaseFragment<CompanyPresenter> implements CompanyContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company;
    }

    @Override
    protected void initEventAndData() {

    }

}
