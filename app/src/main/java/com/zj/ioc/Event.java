package com.zj.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wang
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Event {
    /**
     * btn1.setOnClickListener(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View v) {
     *
     *             }
     *         });
     */
    String listenerSetter();

    Class<?> listenerType();

}
