package com.lxf.proxy.staticc;

/**
 * Created by xiaofan on 2015/4/3.
 */
public class Test {
    private static IDemo iDemo = new DemoProxy();
    public static void main(String[] args){
        iDemo.say();
    }
}
