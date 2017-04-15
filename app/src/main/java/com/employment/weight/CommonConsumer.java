package com.employment.weight;

import android.text.TextUtils;

import com.employment.base.BaseView;

import org.reactivestreams.Subscriber;

/**
 * Created by roy on 2017/4/9.
 */

public abstract class CommonConsumer<T> implements Subscriber<T> {

    private BaseView mView;

    private String mErrorMsg;


    public CommonConsumer(BaseView mView, String mErrorMsg) {
        this.mView = mView;
        this.mErrorMsg = mErrorMsg;
    }

    @Override
    public void onError(Throwable t) {
        if (mView == null)
            return;
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showError(mErrorMsg);
        } else {
            mView.showError("数据加载失败ヽ(≧Д≦)ノ");
        }
    }
}
