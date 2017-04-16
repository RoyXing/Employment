package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;
import com.employment.model.student.bean.Note;

import java.util.List;

/**
 * Created by roy on 2017/4/9.
 */

public interface NoteContract {

    interface View extends BaseView {
        void showContent(List<Note> list);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchNoteInfo();
    }
}
