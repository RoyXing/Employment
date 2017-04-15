package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.NoteContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class NotePresenter extends RxPresenter<NoteContract.View> implements NoteContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public NotePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
