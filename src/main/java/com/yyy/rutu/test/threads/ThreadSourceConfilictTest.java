package com.yyy.rutu.test.threads;

public class ThreadSourceConfilictTest {

    // 临界资源问题
    // 通过景点售票员同时卖票说明
    public static void main(String[] args){
        // 实例化4个售票员：用4个线程表示
        Runnable r = () -> {
            // 只要还有剩余的票，就可以继续卖
            while (TicketCenter.restCount > 0){
                System.out.println(Thread.currentThread().getName()+ " 卖出了一张票，剩余："
                        + --TicketCenter.restCount + "张");
            }
        };
        Thread t1 = new Thread(r, "thread1");
        Thread t2 = new Thread(r, "thread2");
        Thread t3 = new Thread(r, "thread3");
        Thread t4 = new Thread(r, "thread4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class TicketCenter{
    public static int restCount = 100;//假设剩余100张票
}
