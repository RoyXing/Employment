package com.employment.model.student.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.employment.R;
import com.employment.base.SimpleBaseFragment;
import com.employment.model.student.bean.Recruit;

import butterknife.BindView;

/**
 * Created by roy on 2017/4/15.
 */

public class EmploymentDetails extends SimpleBaseFragment {

    private static final String ARG_NUMBER = "arg_number";

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

    private Recruit recruit;

    public static EmploymentDetails newInstance(Recruit recruit) {
        EmploymentDetails fragment = new EmploymentDetails();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NUMBER, recruit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            recruit = (Recruit) args.getSerializable(ARG_NUMBER);
        }
    }

    @Override
    protected int getLayoutId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = mActivity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }        return R.layout.fragment_employment_detail;
    }

    @Override
    protected void initEventAndData() {
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
        collapsingToolbar.setTitle(recruit.getCmCompanyByCid().getCname());
        Glide.with(this).load(recruit.getCmCompanyByCid().getCface()).centerCrop().into(companyDetailImage);
        companyDetailText.setText(recruit.getCmCompanyByCid().getCinfo());
        companyDetailPosition.setText(recruit.getRinfo());
    }

}
