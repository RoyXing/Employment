package com.employment.model.company.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.CheckPresenter;
import com.employment.presenter.contract.CheckContract;

/**
 * Created by roy on 2017/3/28.
 */

public class CheckFragment extends BaseFragment<CheckPresenter> implements CheckContract.View {

    public static CheckFragment newInstance(){return new CheckFragment();}

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check;
    }

    @Override
    protected void initEventAndData() {

    }
}
