package com.employment.model.student.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.CommunityPresenter;
import com.employment.presenter.contract.CommunityContract;

/**
 * Created by roy on 2017/3/28.
 */

public class CommunityFragment extends BaseFragment<CommunityPresenter> implements CommunityContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initEventAndData() {

    }
}
