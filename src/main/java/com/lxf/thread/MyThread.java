package com.lxf.thread;

/**
 * 线程类,实现Runnable接口可以实现资源共享
 * @author linxiaofan
 */
public class MyThread implements Runnable{
	private int ticket = 5;

	@Override
	public void run() {
        sale();
    }

    public synchronized void sale(){
        while(ticket>0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余票数:" + (this.ticket--));
            }
        }
    }

    public static void main(String []args){
        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(myThread,"窗口A");
        Thread thread2 = new Thread(myThread,"窗口B");
        Thread thread3 = new Thread(myThread,"窗口C");
        Thread thread4 = new Thread(myThread,"窗口D");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}








