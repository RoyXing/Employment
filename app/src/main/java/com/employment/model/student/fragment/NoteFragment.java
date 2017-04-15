package com.employment.model.student.fragment;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.NotePresenter;
import com.employment.presenter.contract.NoteContract;

/**
 * Created by roy on 2017/3/28.
 */

public class NoteFragment extends BaseFragment<NotePresenter> implements NoteContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_note;
    }

    @Override
    protected void initEventAndData() {

    }
}

