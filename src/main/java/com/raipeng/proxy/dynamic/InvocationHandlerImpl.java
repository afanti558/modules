package com.raipeng.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xiaofan on 2015/4/4.
 * InvocationHandlerImple实现InvocationHandler接口，覆写invoke()方法
 * 代理主题的业务写在invoke()方法中
 */
public class InvocationHandlerImpl implements InvocationHandler {
    private Object target;

    public InvocationHandlerImpl(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("target : " + target.getClass().getName());
        System.out.println("proxy : " + proxy.getClass().getName());
        System.out.println("method : " + method.getName());
        System.out.println("args ： " + args);
        System.out.println("=================================");
        System.out.println("begin the real theme");
        int result = (int) method.invoke(target,args);
        System.out.println("after the real theme");
        System.out.println("=================================");
        return result;
    }
}
