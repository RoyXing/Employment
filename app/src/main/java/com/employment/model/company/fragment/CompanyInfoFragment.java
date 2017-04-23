package com.employment.model.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.model.company.bean.CompanyInfo;
import com.employment.presenter.CompanyInfoPresenter;
import com.employment.presenter.contract.CompanyInfoContract;
import com.employment.utils.SystemUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by roy on 2017/3/28.
 */

public class CompanyInfoFragment extends BaseFragment<CompanyInfoPresenter> implements CompanyInfoContract.View {

    @BindView(R.id.company_info_name)
    TextView companyInfoName;
    @BindView(R.id.company_info_address)
    TextView companyInfoAddress;
    @BindView(R.id.company_info_build_time)
    TextView companyInfoBuildTime;
    @BindView(R.id.company_info_phone)
    TextView companyInfoPhone;
    @BindView(R.id.company_info_email)
    TextView companyInfoEmail;
    @BindView(R.id.company_info_hr)
    TextView companyInfoHr;
    @BindView(R.id.company_info_info)
    TextView companyInfoInfo;
    @BindView(R.id.company_info_layout)
    LinearLayout companyInfoLayout;

    private boolean isNeedCommit;

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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getCompanyInfo();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        if (isNeedCommit)
            mPresenter.commitCompanyInfo();
    }

    @Override
    public void showContent(CompanyInfo companyInfo) {
        companyInfoName.setText(companyInfo.getCname() + "");
        companyInfoAddress.setText(companyInfo.getCaddress() + "");
        companyInfoBuildTime.setText(companyInfo.getCtime());
        companyInfoPhone.setText(companyInfo.getCphone() + "");
        companyInfoEmail.setText(companyInfo.getCemail() + "");
        companyInfoHr.setText(companyInfo.getChr() + "");
        companyInfoInfo.setText(companyInfo.getCinfo() + "");
    }

    @Override
    public void showModifyInfo(String newInfo, String type) {
        isNeedCommit = true;
        switch (type) {
            case "0":
                companyInfoAddress.setText(newInfo);
                break;
            case "1":
                companyInfoPhone.setText(newInfo);
                break;
            case "2":
                companyInfoEmail.setText(newInfo);
                break;
            case "3":
                companyInfoHr.setText(newInfo);
                break;
            case "4":
                companyInfoInfo.setText(newInfo);
                break;
        }
    }

    @Override
    public void showBuildTime(String time) {
        isNeedCommit = true;
        companyInfoBuildTime.setText(time);
    }

    @Override
    public void companyInfoCommitSuccess() {
        isNeedCommit = false;
    }

    @OnClick({R.id.company_info_address, R.id.company_info_build_time, R.id.company_info_phone, R.id.company_info_email, R.id.company_info_hr, R.id.company_info_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.company_info_address:
                mPresenter.modifyInfo(companyInfoLayout, companyInfoAddress.getText().toString(), "0");
                break;
            case R.id.company_info_build_time:
                mPresenter.setTime(SystemUtils.string2Long(companyInfoBuildTime.getText().toString()));
                break;
            case R.id.company_info_phone:
                mPresenter.modifyInfo(companyInfoLayout, companyInfoPhone.getText().toString(), "1");
                break;
            case R.id.company_info_email:
                mPresenter.modifyInfo(companyInfoLayout, companyInfoEmail.getText().toString(), "2");
                break;
            case R.id.company_info_hr:
                mPresenter.modifyInfo(companyInfoLayout, companyInfoHr.getText().toString(), "3");
                break;
            case R.id.company_info_info:
                mPresenter.modifyInfo(companyInfoLayout, companyInfoInfo.getText().toString(), "4");
                break;
        }
    }
}
