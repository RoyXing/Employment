package com.employment.base;

/**
 * Created by roy on 2017/4/7.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
