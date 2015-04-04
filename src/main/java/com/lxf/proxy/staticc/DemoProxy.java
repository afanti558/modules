package com.lxf.proxy.staticc;

/**
 * Created by xiaofan on 2015/4/3.
 */
public class DemoProxy implements IDemo {

    DemoImpl demoImpl = new DemoImpl();

    @Override
    public void say() {
        System.out.println("begin the real theme");
        demoImpl.say();
        System.out.println("after the real theme");
    }
}
