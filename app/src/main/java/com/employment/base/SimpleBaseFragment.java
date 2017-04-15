package com.employment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by roy on 2017/3/27.
 */

public abstract class SimpleBaseFragment extends SupportFragment {

    protected View mView;
    protected AppCompatActivity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (savedInstanceState == null) {
            if (!isHidden()) {
                isInited = true;
                initEventAndData();
            } else {
                if (isSupportVisible()) {
                    isInited = true;
                    initEventAndData();
                }
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
            initEventAndData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}
