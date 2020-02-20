package com.zj.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ListenerInvocationHandler implements InvocationHandler {

    private Object context;

    private Method method;

    public ListenerInvocationHandler(Object context, Method method) {
        this.context = context;
        this.method = method;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.method.invoke(context, args);
    }
}
