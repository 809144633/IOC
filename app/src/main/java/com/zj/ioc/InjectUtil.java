package com.zj.ioc;

import android.content.Context;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectUtil {
    private Class<?> cls;

    public void inject(Object context) {
        cls = context.getClass();

        injectContentView(context);

        injectView(context);

        injectClick(context);

    }

    private void injectClick(Object context) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> acls = annotation.annotationType();
                Event event = acls.getAnnotation(Event.class);
                if (event != null) {
                    String listenerSetter = event.listenerSetter();
                    Class<?> listenerType = event.listenerType();
                    try {
                        Method value = acls.getDeclaredMethod("value");
                        int[] ids = (int[]) value.invoke(annotation);
                        for (int id : ids) {
                            View view = (View) cls
                                    .getMethod("findViewById", int.class)
                                    .invoke(context, id);

                            if (view != null) {
                                ListenerInvocationHandler handler =
                                        new ListenerInvocationHandler(context, method);
                                Object proxyInstance = Proxy.newProxyInstance(
                                        listenerType.getClassLoader(),
                                        new Class[]{listenerType},
                                        handler);
                                Class<? extends View> vCls = view.getClass();
                                Method setListener = vCls.getMethod(listenerSetter, listenerType);
                                setListener.invoke(view, proxyInstance);
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    private void injectView(Object context) {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            BindViews annotation = field.getAnnotation(BindViews.class);
            if (annotation != null) {
                try {
                    int value = annotation.value();
                    Method findViewById = cls.getMethod("findViewById", int.class);
                    field.setAccessible(true);
                    View view = (View) findViewById.invoke(context, value);
                    field.set(context, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void injectContentView(Object context) {
        try {
            BindContentView annotation = cls.getAnnotation(BindContentView.class);
            if (annotation != null) {
                int viewId = annotation.value();
                Method setContentView = cls.getMethod("setContentView", int.class);
                setContentView.invoke(context, viewId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
