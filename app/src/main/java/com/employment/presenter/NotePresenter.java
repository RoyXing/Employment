package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.model.student.bean.Note;
import com.employment.model.student.event.NoteEvent;
import com.employment.presenter.contract.NoteContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/9.
 */

public class NotePresenter extends RxPresenter<NoteContract.View> implements NoteContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;

    @Inject
    public NotePresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
        registerEvent();
    }

    private void registerEvent() {
        addRxBusSubscribe(NoteEvent.class, new Consumer<NoteEvent>() {
            @Override
            public void accept(@NonNull NoteEvent note) throws Exception {
                if (note.getType() == 1) {
                    fetchNoteInfo();
                }
            }
        });
    }

    @Override
    public void fetchNoteInfo() {
        Disposable disposable = mRetrofitHelper.getAllNote(realmHelper.getStudentInfoBean().getSid() + "")
                .compose(RxUtil.<List<Note>>rxSchedulerHelper())
                .subscribe(new Consumer<List<Note>>() {
                    @Override
                    public void accept(@NonNull List<Note> list) throws Exception {
                        mView.showContent(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError("");
                    }
                });
        addSubscribe(disposable);
    }
}
