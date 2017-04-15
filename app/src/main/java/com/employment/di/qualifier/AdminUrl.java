package com.employment.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by roy on 2017/4/7.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminUrl {
}
