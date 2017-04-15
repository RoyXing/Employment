package com.employment.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.employment.R;
import com.employment.base.BaseActivity;
import com.employment.model.student.bean.Recruit;
import com.employment.presenter.EmploymentDetailsPresenter;
import com.employment.presenter.contract.EmploymentDetailContract;
import com.employment.utils.SystemUtils;

import butterknife.BindView;

public class EmploymentDetailActivity extends BaseActivity<EmploymentDetailsPresenter> implements
        EmploymentDetailContract.View, View.OnClickListener {

    public static final String ARG_NUMBER = "arg_number";

    @BindView(R.id.company_detail_image)
    ImageView companyDetailImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.company_detail_text)
    TextView companyDetailText;
    @BindView(R.id.company_detail_position)
    TextView companyDetailPosition;
    @BindView(R.id.company_detail_total_person)
    TextView companyDetailTotalPerson;
    @BindView(R.id.company_detail_publish_time)
    TextView companyDetailPublishTime;
    @BindView(R.id.company_detail_deadline)
    TextView companyDetailDeadline;
    @BindView(R.id.company_phone)
    TextView companyPhone;
    @BindView(R.id.company_email)
    TextView companyEmail;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    private Recruit recruit;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_employment_detail;
    }

    @Override
    protected void initEventAndData() {
        recruit = (Recruit) getIntent().getSerializableExtra(ARG_NUMBER);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbar.setTitle(recruit.getCmCompanyByCid().getCname());
        Glide.with(this).load(recruit.getCmCompanyByCid().getCface()).centerCrop().into(companyDetailImage);
        companyDetailText.setText(recruit.getCmCompanyByCid().getCinfo());
        companyDetailPosition.setText(recruit.getRinfo());
        companyDetailTotalPerson.setText(recruit.getRnum() + "");
        companyDetailPublishTime.setText(SystemUtils.stampToDate(recruit.getRstart()));
        companyDetailDeadline.setText(SystemUtils.stampToDate(recruit.getRend()));
        companyPhone.setText(recruit.getCmCompanyByCid().getCphone() + "");
        companyEmail.setText(recruit.getCmCompanyByCid().getCemail() + "");
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mPresenter.applyResume(recruit.getRid(), 0);
    }

    @Override
    public void applyOk() {
        SystemUtils.showToast(this, "投递成功，请等待回复");
    }
}
