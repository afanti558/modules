package com.lxf.thread;

/**
 * Created by xiaofan on 2015/3/18.
* 生产者与消费者模型中，要保证以下几点：
* 1 同一时间内只能有一个生产者生产		生产方法加锁sychronized
* 2 同一时间内只能有一个消费者消费		消费方法加锁sychronized
* 3 生产者生产的同时消费者不能消费		生产方法加锁sychronized
* 4 消费者消费的同时生产者不能生产		消费方法加锁sychronized
* 5 共享空间空时消费者不能继续消费		消费前循环判断是否为空，空的话将该线程wait，释放锁允许其他同步方法执行
* 6 共享空间满时生产者不能继续生产		生产前循环判断是否为满，满的话将该线程wait，释放锁允许其他同步方法执行
*/

//主类
public class ProducerConsumer
{
    public static void main(String[] args)
    {
        StackBasket s = new StackBasket();
        Producer p = new Producer(s);
        Consumer c = new Consumer(s);
        Thread tp = new Thread(p);
        Thread tc = new Thread(c);
        tp.start();
        tc.start();
    }
}
