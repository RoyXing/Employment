package com.employment.model.company.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.employment.R;
import com.employment.base.BaseActivity;
import com.employment.model.student.bean.StudentInfo;
import com.employment.presenter.CheckResumeDetailPresenter;
import com.employment.presenter.contract.ResumeDetailContract;
import com.employment.utils.SystemUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by roy on 2017/4/23.
 */

public class CheckResumeDetail extends BaseActivity<CheckResumeDetailPresenter> implements ResumeDetailContract.View {

    @BindView(R.id.personal_name)
    TextView personalName;
    @BindView(R.id.personal_sex)
    TextView personalSex;
    @BindView(R.id.personal_grade)
    TextView personalGrade;
    @BindView(R.id.personal_sno)
    TextView personalSno;
    @BindView(R.id.personal_pro)
    TextView personalPro;
    @BindView(R.id.personal_birthday)
    TextView personalBirthday;
    @BindView(R.id.personal_phone)
    TextView personalPhone;
    @BindView(R.id.personal_email)
    TextView personalEmail;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.personal_pingjia)
    TextView personalPingjia;
    @BindView(R.id.notice_interview)
    Button noticeInterview;
    @BindView(R.id.interview_pass)
    Button interviewPass;
    @BindView(R.id.interview_failure)
    Button interviewFailure;
    @BindView(R.id.interview_refuse)
    Button interviewRefuse;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int interviewId;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_check_resume;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "");
        customTitle.setVisibility(View.VISIBLE);
        customTitle.setText("简历审核");
        interviewId = getIntent().getIntExtra("interviewId", 0);
        StudentInfo studentInfo = (StudentInfo) getIntent().getSerializableExtra("info");
        personalName.setText(studentInfo.getSname() + "");
        if (studentInfo.isSsex())
            personalSex.setText("男");
        else
            personalSex.setText("女");
        personalGrade.setText(studentInfo.getSgrade() + "");
        personalSno.setText(studentInfo.getSno() + "");
        personalPro.setText(studentInfo.getDepartment().getDepName() + "");
        personalBirthday.setText(SystemUtils.formatTime(studentInfo.getSbirth()));
        personalPhone.setText(studentInfo.getSphone() + "");
        personalEmail.setText(studentInfo.getSemail() + "");
        ratingBar.setMax(10);
        ratingBar.setNumStars(5);
        ratingBar.setRating((float) studentInfo.getSmark());
        personalPingjia.setText(studentInfo.getSdetail() + "");
        int status = getIntent().getIntExtra("status", 0);
        if (status == 1) {
            noticeInterview.setEnabled(false);
        } else if (status == 2) {
            interviewPass.setEnabled(false);
        } else if (status == 3) {
            interviewFailure.setEnabled(false);
        } else if (status == 4) {
            interviewFailure.setEnabled(false);
        }

    }

    @OnClick({R.id.notice_interview, R.id.interview_pass, R.id.interview_failure, R.id.interview_refuse})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notice_interview:
                mPresenter.commitResumeStatus(interviewId + "", "1");
                break;
            case R.id.interview_pass:
                mPresenter.commitResumeStatus(interviewId + "", "2");
                break;
            case R.id.interview_failure:
                mPresenter.commitResumeStatus(interviewId + "", "3");
                break;
            case R.id.interview_refuse:
                mPresenter.commitResumeStatus(interviewId + "", "4");
                break;
        }
    }

    @Override
    public void commitSuccess(String type) {
        noticeInterview.setEnabled(true);
        interviewPass.setEnabled(true);
        interviewFailure.setEnabled(true);
        interviewRefuse.setEnabled(true);
        switch (type) {
            case "1":
                noticeInterview.setEnabled(false);
                break;
            case "2":
                interviewPass.setEnabled(false);
                break;
            case "3":
                interviewFailure.setEnabled(false);
                break;
            case "4":
                interviewRefuse.setEnabled(false);
                break;
        }
        SystemUtils.showToast(mContext, "更改成功!");
    }
}
