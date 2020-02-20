package com.zj.ioc;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Event(
        listenerSetter = "setOnClickListener",
        listenerType = View.OnClickListener.class)
public @interface BindClick {
    int[] value();
}
