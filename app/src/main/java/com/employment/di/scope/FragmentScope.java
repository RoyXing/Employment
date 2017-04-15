package com.employment.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by roy on 2017/4/7.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
