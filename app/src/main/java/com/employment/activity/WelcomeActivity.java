package com.employment.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.employment.R;
import com.employment.base.BaseActivity;
import com.employment.http.bean.WelcomeBean;
import com.employment.presenter.WelcomePresenter;
import com.employment.presenter.contract.WelcomeContract;

import butterknife.BindView;

/**
 * Created by roy on 2017/4/7.
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.welcome_image)
    ImageView welcomeImage;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWelcomeImage();
    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
        Glide.with(this).load(welcomeBean.getImg()).into(welcomeImage);
        welcomeImage.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        tvWelcomeAuthor.setText(welcomeBean.getText());
    }

    @Override
    public void jumpWhichPage() {
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void onDestroy() {
        Glide.clear(welcomeImage);
        super.onDestroy();
    }

}
