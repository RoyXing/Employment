package com.employment.model.company.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.employment.R;
import com.employment.base.SimpleBaseActivity;
import com.employment.model.student.bean.StudentInfo;
import com.employment.utils.SystemUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by roy on 2017/4/23.
 */

public class CheckResumeDetail extends SimpleBaseActivity {

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

    @Override
    protected int getLayout() {
        return R.layout.activity_check_resume;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "");
        customTitle.setVisibility(View.VISIBLE);
        customTitle.setText("简历审核");
        StudentInfo studentInfo = (StudentInfo) getIntent().getSerializableExtra("info");
        personalName.setText(studentInfo.getSname() + "");
        if (studentInfo.isSsex())
            personalSex.setText("男");
        else
            personalSex.setText("女");
        personalGrade.setText(studentInfo.getSgrade() + "");
        personalSno.setText(studentInfo.getSno() + "");
        personalPro.setText(studentInfo.getSpro() + "");
        personalBirthday.setText(SystemUtils.formatTime(studentInfo.getSbirth()));
        personalPhone.setText(studentInfo.getSphone() + "");
        personalEmail.setText(studentInfo.getSemail() + "");
        ratingBar.setMax(10);
        ratingBar.setNumStars(5);
        ratingBar.setRating((float) studentInfo.getSmark());
        personalPingjia.setText(studentInfo.getSdetail() + "");
    }

    @OnClick({R.id.notice_interview, R.id.interview_pass, R.id.interview_failure, R.id.interview_refuse})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notice_interview:
                break;
            case R.id.interview_pass:
                break;
            case R.id.interview_failure:
                break;
            case R.id.interview_refuse:
                break;
        }
    }

}
