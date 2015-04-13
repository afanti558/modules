package com.raipeng.pattern.adapter;

import org.junit.Test;

public class TestAdepter {

    //类的适配模式
    @Test
    public void testAdepter_1(){
        Job job = new Adepter_1();
        job.speakEnglish();
        job.speakFrench();
        job.speakJapanese();
    }

    //对象的适配模式
    @Test
    public void testAdepter(){
        Job job = new Adepter(new Person());
        job.speakEnglish();
        job.speakFrench();
        job.speakJapanese();
    }

}
