package com.employment.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by roy on 2017/4/7.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface CompanyUrl {
}
