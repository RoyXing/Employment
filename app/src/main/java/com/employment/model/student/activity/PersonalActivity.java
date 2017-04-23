package com.employment.model.student.activity;

import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.employment.R;
import com.employment.base.BaseActivity;
import com.employment.presenter.PersonalModifyPresenter;
import com.employment.presenter.contract.PersonalModifyContract;
import com.employment.utils.SystemUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by roy on 2017/4/17.
 */

public class PersonalActivity extends BaseActivity<PersonalModifyPresenter> implements PersonalModifyContract.View, AdapterView.OnItemSelectedListener {

    @BindView(R.id.person_self_detail)
    EditText personSelfDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.personal_activity_self_comment)
    CardView commentLayout;
    @BindView(R.id.personal_graduate_status_spinner)
    AppCompatSpinner spinner;
    @BindView(R.id.cardView_employment_layout)
    CardView cardViewEmploymentLayout;
    @BindView(R.id.personal_unEmployment_layout)
    LinearLayout personalUnEmploymentLayout;
    @BindView(R.id.personal_employment_layout)
    LinearLayout personalEmploymentLayout;
    @BindView(R.id.personal_expect_position1)
    EditText personalExpectPosition1;
    @BindView(R.id.personal_expect_salary1)
    EditText personalExpectSalary1;
    @BindView(R.id.personal_stay_position1)
    EditText personalStayPosition1;
    @BindView(R.id.personal_stay_salary1)
    EditText personalStaySalary1;
    @BindView(R.id.personal_stay_time1)
    TextView personalStayTime1;

    private String type;

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
        setToolBar(toolbar, "");
        type = getIntent().getStringExtra("type");
        if (type.equals("0")) {
            commentLayout.setVisibility(View.VISIBLE);
            String selfInfo = getIntent().getStringExtra("selfInfo");
            personSelfDetail.setText(selfInfo + "");
        } else if (type.equals("1")) {
            cardViewEmploymentLayout.setVisibility(View.VISIBLE);
            spinner.setOnItemSelectedListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.personal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.personal_info_store) {
            if (type.equals("0")) {
                mPresenter.setSelfComment(personSelfDetail.getText().toString());
            } else if (type.equals("1")) {
                cardViewEmploymentLayout.setVisibility(View.VISIBLE);
                if (personalEmploymentLayout.getVisibility() == View.VISIBLE) {//更改的就业状态
                    mPresenter.commitEmploymentInfo("5", personalStayPosition1.getText().toString(),
                            personalStaySalary1.getText().toString(), personalStayTime1.getText().toString().equals("") ?
                                    SystemUtils.formatTime(new Date()) : personalStayTime1.getText().toString());
                } else if (personalUnEmploymentLayout.getVisibility() == View.VISIBLE) {
                    mPresenter.commitUnEmployment("4", personalExpectPosition1.getText().toString(),
                            personalExpectSalary1.getText().toString());
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCommentSuccess() {
        finishAfterTransition();
    }

    @Override
    public void showStayDate(String date) {
        personalStayTime1.setText(date);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String[] status = getResources().getStringArray(R.array.status);
        switch (status[i]) {
            case "待就业":
                personalUnEmploymentLayout.setVisibility(View.VISIBLE);
                personalEmploymentLayout.setVisibility(View.GONE);
                break;
            case "就业":
                personalUnEmploymentLayout.setVisibility(View.GONE);
                personalEmploymentLayout.setVisibility(View.VISIBLE);
                break;
            case "延长学制":

                break;
            case "留学":

                break;
            case "考研":

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @OnClick(R.id.personal_stay_time1)
    public void onViewClicked() {
        if (personalStayTime1.getText().toString().isEmpty())
            mPresenter.chooseStayTime(new Date());
        else
            mPresenter.chooseStayTime(SystemUtils.string2Date(personalStayTime1.getText().toString()));
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finishAfterTransition();
        }
    }
}
