/******************************************************************************
 * Copyright (c) 2015. By tuanna (Jackson Nguyen)                             *
 ******************************************************************************/

package com.tuanna.otto_lombokdemo.bus;

import com.squareup.otto.Bus;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

/**
 * Otto-LombokDemo.
 * Created by tuanna on 28/10/2015.
 */
@EBean(scope = Scope.Singleton)
public class BusProvider extends Bus {
}
