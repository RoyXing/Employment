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
import com.employment.utils.SharedPreferenceUtil;

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
        welcomeImage.setScaleX(0.8f);
        welcomeImage.setScaleY(0.8f);
        Glide.with(this).load("http://wgyxy.lcu.edu.cn/my-uploads/domain/wgyxy.lcu.edu.cn/2015/01/A4-690x407.jpg").into(welcomeImage);
        welcomeImage.animate().scaleX(1.0f).scaleY(1.0f).setDuration(1800).setStartDelay(100).start();
        tvWelcomeAuthor.setText("DESIGN BY" + " XZY");
    }

    @Override
    public void jumpWhichPage() {
        Intent intent = new Intent();
        if (SharedPreferenceUtil.getIsLogin())
            intent.setClass(this, MainActivity.class);
        else
            intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        Glide.clear(welcomeImage);
        super.onDestroy();
    }
}
