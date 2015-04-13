package com.raipeng.thread;

/**
 * 线程的强制执行、休眠
 * @author linxiaofan
 */
public class Thread_join implements Runnable{
    @Override
    public void run() {
        try {
            join(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void join(Thread thread) throws InterruptedException {
        thread.sleep(1000);
        System.out.println(thread.getName() + "运行");
        thread.sleep(1000);
    }

    public static void main(String []args){
        Thread_join myThread = new Thread_join();
        Thread thread1 = new Thread(myThread,"自定义线程");
        thread1.start();
        for (int i = 0; i < 10; i++) {
            if (i>3){
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread1.interrupt();
            }
            System.out.println("main线程运行 : " + i);
        }

    }

}
