package com.employment.presenter.contract;

import com.employment.base.BasePresenter;
import com.employment.base.BaseView;

/**
 * Created by roy on 2017/4/16.
 */

public interface NoteEditContract {

    interface View extends BaseView {

        void addSuccess();

        void deleteSuccess();

        void modifySuccess();
    }

    interface Presenter extends BasePresenter<View> {

        void addNote( String title, String content);

        void deleteNote(String noteId);

        void modifyNote(String noteId, String title, String content);
    }
}
