package com.employment.db;

import android.util.Log;

import com.employment.model.admin.bean.AdminInfo;
import com.employment.model.company.bean.CompanyInfo;
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

    public void insertAdminInfo(final AdminInfo adminInfo) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(adminInfo);
            }
        });
    }

    public void insertCompanyInfo(final CompanyInfo companyInfo) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(companyInfo);
            }
        });
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

    public CompanyInfo getCompanyInfoBean() {
        CompanyInfo first = mRealm.where(CompanyInfo.class).findFirst();
        return mRealm.copyFromRealm(first);
    }

    public AdminInfo getAdminInfoBean() {
        AdminInfo first = mRealm.where(AdminInfo.class).findFirst();
        return mRealm.copyFromRealm(first);
    }

    public StudentInfo getStudentInfoBean() {
        StudentInfo first = mRealm.where(StudentInfo.class).findFirst();
        return mRealm.copyFromRealm(first);
    }

    public void setEmploymentStatus(final int status) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(StudentInfo.class).findFirst().setSstate(status);
            }
        });
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

    public void setCompanyInfo(final String info, final String type) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CompanyInfo companyInfo = realm.where(CompanyInfo.class).findFirst();
                switch (type) {
                    case "0":
                        companyInfo.setCaddress(info);
                        break;
                    case "1":
                        companyInfo.setCphone(info);
                        break;
                    case "2":
                        companyInfo.setCemail(info);
                        break;
                    case "3":
                        companyInfo.setChr(info);
                        break;
                    case "4":
                        companyInfo.setCinfo(info);
                        break;
                }
            }
        });
    }

    public void setCompanyBuildTile(final String time) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(CompanyInfo.class).findFirst().setCtime(time);
            }
        });
    }

    public void clearUserInfo() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.where(StudentInfo.class).findAll().deleteAllFromRealm();
                mRealm.where(CompanyInfo.class).findAll().deleteAllFromRealm();
                mRealm.where(AdminInfo.class).findAll().deleteAllFromRealm();
            }
        });
    }
}
