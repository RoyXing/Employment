package com.employment.app;

import java.io.File;

/**
 * Created by roy on 2017/3/27.
 */

public class Constants {

    public static final int TYPE_RECRUITMENT = 101;

    public static final int TYPE_COMPANY = 102;

    public static final int TYPE_COMMUNITY = 103;

    public static final int TYPE_PERSONAL_INFO = 104;

    public static final int TYPE_NOTE = 105;


    public static final int TYPE_CHECK_INFO = 106;

    public static final int TYPE_COMPANY_INFO = 107;

    public static final int TYPE_PUBLISH = 108;

    public static final int TYPE_CHECK_RESUME = 109;


    public static final int TYPE_CHECK_RECRUITMENT_STATUS = 110;

    public static final int TYPE_CHECK_RECRUITMENT_INFO = 111;

    public static final int TYPE_CHECK_POST = 112;

    public static final String EMPLOYMENT = "employment_tag";

    public static final String USER_STATUS = "user_status";

    public static final String HOST = "http://115.159.100.155:8080/CareerManage/api/";

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/cache";
}
