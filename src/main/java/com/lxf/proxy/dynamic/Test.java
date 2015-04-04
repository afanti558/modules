package com.lxf.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * Created by xiaofan on 2015/4/3.
 */
public class Test {

    public static void main(String[] args){

    IDemo iDemo = new DemoImpl();

    //取得代理对象
        IDemo iDemoProxy = (IDemo) Proxy.newProxyInstance(iDemo.getClass().getClassLoader(),
            iDemo.getClass().getInterfaces(), new InvocationHandlerImpl(iDemo));

    //通过动态代理调用方法
        iDemoProxy.say("参数");
    }
}
