package com.employment.model.student.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.model.student.activity.NoteActivity;
import com.employment.model.student.activity.PersonalActivity;
import com.employment.model.student.bean.Employment;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.bean.UnEmployment;
import com.employment.presenter.PersonalPresenter;
import com.employment.presenter.contract.PersonalContract;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class PersonalFragment extends BaseFragment<PersonalPresenter> implements PersonalContract.View, View.OnClickListener {

    @BindView(R.id.personal_info_layout)
    CardView personalInfoLayout;
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
    @BindView(R.id.personal_graduate_status)
    TextView personalGraduateStatus;
    @BindView(R.id.personal_expect_position)
    TextView personalExpectPosition;
    @BindView(R.id.personal_expect_salary)
    TextView personalExpectSalary;
    @BindView(R.id.unEmployment_layout)
    LinearLayout unEmploymentLayout;
    @BindView(R.id.personal_stay_position)
    TextView personalStayPosition;
    @BindView(R.id.personal_stay_salary)
    TextView personalStaySalary;
    @BindView(R.id.personal_stay_time)
    TextView personalStayTime;
    @BindView(R.id.employment_layout)
    LinearLayout employmentLayout;

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
        unEmploymentLayout.setOnClickListener(this);
        employmentLayout.setOnClickListener(this);
        personalInfoLayout.setOnClickListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getStudentInfo();
        mPresenter.getEmployInfo();
    }

    @Override
    public void showContent(StudentInfo studentInfo) {

        personalName.setText(studentInfo.getSname() + "");
        if (studentInfo.isSsex())
            personalSex.setText("男");
        else
            personalSex.setText("女");
        personalGrade.setText(studentInfo.getSgrade() + "");
        personalSno.setText(studentInfo.getSno() + "");
        personalPro.setText(studentInfo.getSpro() + "");
        personalBirthday.setText(studentInfo.getSbirth() + "");
        personalPhone.setText(studentInfo.getSphone() + "");
        personalEmail.setText(studentInfo.getSemail() + "");
        ratingBar.setMax(10);
        ratingBar.setNumStars(5);
        ratingBar.setRating((float) studentInfo.getSmark());
        personalPingjia.setText(studentInfo.getSdetail() + "");
    }

    @Override
    public void showEmploymentInfo(Employment employment) {
    }

    @Override
    public void showUnEmploymentInfo(UnEmployment unEmployment) {
        unEmploymentLayout.setVisibility(View.VISIBLE);
        employmentLayout.setVisibility(View.GONE);
        personalGraduateStatus.setText("未就业");
        personalExpectPosition.setText(unEmployment.getCmJobByJid().getJname() + "");
        personalExpectSalary.setText(unEmployment.getUesalary() + "");
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.unEmployment_layout:
                Intent intent = new Intent(mContext, PersonalActivity.class);
                intent.putExtra("info", "info");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, "personalInfo");
                mContext.startActivity(intent, options.toBundle());
                break;
            case R.id.employment_layout:
                break;
        }
    }
}
