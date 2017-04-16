package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.http.bean.ResponseBean;
import com.employment.presenter.contract.NoteEditContract;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/16.
 */

public class NoteEditPresenter extends RxPresenter<NoteEditContract.View> implements NoteEditContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;

    @Inject
    public NoteEditPresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void addNote(String title, String content) {
        HashMap<String, String> map = new HashMap<>();
        map.put("studentId", realmHelper.getStudentInfoBean().getSid() + "");
        map.put("title", title);
        map.put("content", content);
        Disposable disposable = mRetrofitHelper.addNote(map)
                .compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        if (responseBean.getCode() == 200) {
                            mView.addSuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void deleteNote(String noteId) {
        Disposable disposable = mRetrofitHelper.deleteNote(noteId)
                .compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        if (responseBean.getCode() == 200) {
                            mView.deleteSuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void modifyNote(String noteId, String title, String content) {
        HashMap<String, String> map = new HashMap<>();
        map.put("noteId", noteId);
        map.put("title", title);
        map.put("content", content);
        Disposable disposable = mRetrofitHelper.updateNote(map)
                .compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        if (responseBean.getCode() == 200) {
                            mView.modifySuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(disposable);
    }
}
