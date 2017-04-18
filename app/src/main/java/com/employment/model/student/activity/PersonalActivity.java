package com.employment.model.student.activity;

import com.employment.R;
import com.employment.base.BaseActivity;
import com.employment.presenter.PersonalModifyPresenter;
import com.employment.presenter.contract.PersonalModifyContract;

/**
 * Created by roy on 2017/4/17.
 */

public class PersonalActivity extends BaseActivity<PersonalModifyPresenter> implements PersonalModifyContract.View {


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_personal_modify;
    }

    @Override
    protected void initEventAndData() {

    }
}
