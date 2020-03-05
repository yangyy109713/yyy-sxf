package com.yyy.rutu.test.threads;

public class SynchronizedTest {

    public static void main(String[] args){
        // 实例化4个售票员：用4个线程表示
        Runnable r = () -> {
            // 只要还有剩余的票，就可以继续卖
            while (TicketCenter.restCount > 0){
                /* 1.对象锁：给对象添加锁，对象可以是任意的。synchronized ("")
                   2.类锁： synchronized (SynchronizedTest.class)
                    需要保证一点：多个线程看到的锁，需要是同一把锁
                    如 SynchronizedTest.class，此时每个线程看到的都是当前类.class
                    如果改成 new SynchronizedTest()，则每个线程看到的锁都不一样
                */
                synchronized (SynchronizedTest.class){
                    if(TicketCenter.restCount > 0) {
                        System.out.println(Thread.currentThread().getName() + " 卖出了一张票，剩余："
                                + --TicketCenter.restCount + "张");
                    }
                }
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
