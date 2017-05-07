package com.employment.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.employment.R;
import com.employment.app.App;
import com.employment.base.BaseActivity;
import com.employment.presenter.LoginPresenter;
import com.employment.presenter.contract.LoginContract;
import com.employment.utils.SharedPreferenceUtil;
import com.employment.utils.SystemUtils;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.radio)
    RadioGroup radio;

    private int status = 2;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "");
        customTitle.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        customTitle.setText(getString(R.string.sign_in));
        radio.setOnCheckedChangeListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.student:
                status = 2;
                break;
            case R.id.company:
                status = 3;
                break;
            case R.id.admin:
                status = 1;
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                SharedPreferenceUtil.setUserStatus(status);
                mPresenter.login(etAccount.getText().toString(), etPassword.getText().toString(), status + "");
                break;
        }
    }

    @Override
    public void loginSuccess() {
        SharedPreferenceUtil.setIsLogin(true);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginFailure() {
        SystemUtils.showToast(this, "登录失败，请重新登录");
    }

    @Override
    public void showError(String msg) {

    }
}
