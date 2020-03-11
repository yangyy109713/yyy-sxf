package com.yyy.rutu.test.threads;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock();

        Runnable r = () -> {
            while (TicketCenter.restCount > 0){
                lock.lock();//对临界资源上锁
                if(TicketCenter.restCount > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖出了一张票，剩余："
                            + --TicketCenter.restCount + "张");
                }
                lock.unlock();//对临界资源解锁
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
