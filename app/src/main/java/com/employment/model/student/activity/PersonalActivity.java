package com.employment.model.student.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.employment.R;
import com.employment.app.App;
import com.employment.base.BaseActivity;
import com.employment.presenter.PersonalModifyPresenter;
import com.employment.presenter.contract.PersonalModifyContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roy on 2017/4/17.
 */

public class PersonalActivity extends BaseActivity<PersonalModifyPresenter> implements PersonalModifyContract.View {


    @BindView(R.id.person_self_detail)
    EditText personSelfDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.personal_activity_self_comment)
    LinearLayout commentLayout;
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

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCommentSuccess() {
        finishAfterTransition();
    }
}
