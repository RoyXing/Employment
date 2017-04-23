package com.employment.model.student.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.employment.R;
import com.employment.base.BaseActivity;
import com.employment.base.RxBus;
import com.employment.model.student.bean.Recruit;
import com.employment.model.student.bean.Resume;
import com.employment.model.student.event.NoteEvent;
import com.employment.model.student.event.ResumeEvent;
import com.employment.presenter.EmploymentDetailsPresenter;
import com.employment.presenter.contract.EmploymentDetailContract;
import com.employment.utils.SystemUtils;

import java.util.List;

import butterknife.BindView;

public class EmploymentDetailActivity extends BaseActivity<EmploymentDetailsPresenter> implements
        EmploymentDetailContract.View, View.OnClickListener {

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
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    private Recruit recruit;
    private int checkInfo;

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
        checkInfo = getIntent().getIntExtra(CHECK_INFO, 0);
        if (checkInfo == 1)
            floatingActionButton.setVisibility(View.GONE);
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
        companyDetailPublishTime.setText(recruit.getRstart());
        companyDetailDeadline.setText(recruit.getRend());
        companyPhone.setText(recruit.getCmCompanyByCid().getCphone() + "");
        companyEmail.setText(recruit.getCmCompanyByCid().getCemail() + "");
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mPresenter.isApply();
    }

    @Override
    public void applyOk() {
        RxBus.getInstance().post(new ResumeEvent(1));
        SystemUtils.showToast(this, "投递成功，请等待回复");
    }

    @Override
    public void isCanApply(List<Resume> resumes) {
        boolean isCanApply = true;
        for (Resume resume : resumes) {
            if (resume.getCmRecruitByRid().getRid() == recruit.getRid()) {
                isCanApply = false;
            }
        }
        if (isCanApply) {
            mPresenter.applyResume(recruit.getRid(), 0);
        } else
            SystemUtils.showToast(this, "已经投递，请去面试中心查看状态");
    }
}
