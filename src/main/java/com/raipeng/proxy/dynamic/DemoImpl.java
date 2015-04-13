package com.raipeng.proxy.dynamic;

/**
 * Created by xiaofan on 2015/4/3.
 */
public class DemoImpl implements IDemo {
    @Override
    public int say(String str) {
        System.out.println("this is printed in function say. ");
        return 0;
    }
}
