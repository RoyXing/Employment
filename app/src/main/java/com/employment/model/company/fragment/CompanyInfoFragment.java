package com.employment.model.company.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.CompanyInfoPresenter;
import com.employment.presenter.contract.CompanyInfoContract;

/**
 * Created by roy on 2017/3/28.
 */

public class CompanyInfoFragment extends BaseFragment<CompanyInfoPresenter> implements CompanyInfoContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_info;
    }

    @Override
    protected void initEventAndData() {

    }
}
