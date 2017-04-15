package com.employment.model.company.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.PublishPresenter;
import com.employment.presenter.contract.PublishContract;

/**
 * Created by roy on 2017/3/28.
 */

public class PublishFragment extends BaseFragment<PublishPresenter> implements PublishContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_publish;
    }

    @Override
    protected void initEventAndData() {

    }
}
