package com.employment.model.admin.activity;

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
import com.employment.presenter.CheckEmploymentDetailPresenter;
import com.employment.presenter.CheckRecruitmentPresenter;
import com.employment.presenter.EmploymentDetailsPresenter;
import com.employment.presenter.contract.CheckEmploymentDetailContract;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class CheckEmploymentDetailActivity extends BaseActivity<CheckEmploymentDetailPresenter> implements
        CheckEmploymentDetailContract.View, View.OnClickListener {

    public static final String ARG_NUMBER = "arg_number";
    public static final String CHECK_INFO = "check_info";

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
    @BindView(R.id.floatingActionButton_pass)
    FloatingActionButton floatingActionButtonPass;
    @BindView(R.id.floatingActionButton_no)
    FloatingActionButton floatingActionButtonNo;
    private Recruit recruit;
    private int type;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_check_employment_detail;
    }

    @Override
    protected void initEventAndData() {
        recruit = (Recruit) getIntent().getSerializableExtra(ARG_NUMBER);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        type = Integer.parseInt(getIntent().getStringExtra(CHECK_INFO));
        if (type != 2) {
            floatingActionButtonNo.setVisibility(View.GONE);
            floatingActionButtonPass.setVisibility(View.GONE);
        }
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
        companyDetailPublishTime.setText(recruit.getRstart());
        companyDetailDeadline.setText(recruit.getRend());
        companyPhone.setText(recruit.getCmCompanyByCid().getCphone() + "");
        companyEmail.setText(recruit.getCmCompanyByCid().getCemail() + "");
        floatingActionButtonPass.setOnClickListener(this);
        floatingActionButtonNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("recruitId", recruit.getRid() + "");
        switch (view.getId()) {
            case R.id.floatingActionButton_pass:
                map.put("rstate", "0");
                mPresenter.pass(map, "0");
                break;
            case R.id.floatingActionButton_no:
                map.put("rstate", "3");
                mPresenter.pass(map, "3");
                break;
        }
    }


}
