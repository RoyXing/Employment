package com.employment.model.student.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.PersonalPresenter;
import com.employment.presenter.contract.PersonalContract;

/**
 * Created by roy on 2017/3/28.
 */

public class PersonalFragment extends BaseFragment<PersonalPresenter> implements PersonalContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showError(String msg) {

    }
}
