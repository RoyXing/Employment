package com.employment.db;

import android.util.Log;

import com.employment.model.student.bean.StudentInfo;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by roy on 2017/4/15.
 */

public class RealmHelper {

    private static final String DB_NAME = "myRealm.realm";

    private Realm mRealm;

    public RealmHelper() {
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .name(DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        mRealm = Realm.getDefaultInstance();
    }

    public void insertStudentInfo(final StudentInfo json) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(json);
                Log.e("roy", "ok");
            }
        });
    }

    public StudentInfo getStudentInfoBean() {
        StudentInfo first = mRealm.where(StudentInfo.class).findFirst();
        return mRealm.copyFromRealm(first);
    }

}
