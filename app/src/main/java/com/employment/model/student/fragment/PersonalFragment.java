package com.employment.model.student.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.model.student.bean.StudentInfo;
import com.employment.presenter.PersonalPresenter;
import com.employment.presenter.contract.PersonalContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by roy on 2017/3/28.
 */

public class PersonalFragment extends BaseFragment<PersonalPresenter> implements PersonalContract.View {

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
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getStudentInfo();
    }

    @Override
    public void showContent(StudentInfo studentInfo) {
        personalName.setText(studentInfo.getSname()+"");
        if (studentInfo.isSsex())
            personalSex.setText("男");
        else
            personalSex.setText("女");
        personalGrade.setText(studentInfo.getSgrade()+"");
        personalSno.setText(studentInfo.getSno()+"");
        personalPro.setText(studentInfo.getSpro()+"");
        personalBirthday.setText(studentInfo.getSbirth()+"");
        personalPhone.setText(studentInfo.getSphone()+"");
        personalEmail.setText(studentInfo.getSemail()+"");
        ratingBar.setMax(10);
        ratingBar.setNumStars(5);
        ratingBar.setRating((float)studentInfo.getSmark());
        personalPingjia.setText(studentInfo.getSdetail()+"");
//        personalGraduateStatus.setText();
    }
}
