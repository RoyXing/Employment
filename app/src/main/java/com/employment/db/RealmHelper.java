package com.employment.db;

import android.util.Log;

import com.employment.model.student.bean.StudentInfo;

import java.util.Date;

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

    public void setBirthday(final Date date) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(StudentInfo.class).findFirst().setSbirth(date);
            }
        });
    }

    public void setPhone(final String phone) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(StudentInfo.class).findFirst().setSphone(phone);
            }
        });
    }

    public void setEmail(final String email) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(StudentInfo.class).findFirst().setSemail(email);
            }
        });
    }

    public void setSelfComment(final String detail) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(StudentInfo.class).findFirst().setSdetail(detail);
            }
        });
    }

}
