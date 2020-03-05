package com.yyy.rutu.test.threads;

public class SynchronizedFuncTest {

    public static void main(String[] args){
        // 锁--同步方法：使用关键字 synchronized 修饰的方法
        // 实例化4个售票员：用4个线程表示
        Runnable r = () -> {
            // 只要还有剩余的票，就可以继续卖
            while (TicketCenter.restCount > 0){
                setTicket();
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

    /**
     * 同步方法
     * 此时的同步锁是什么？
     * 如果是静态方法，同步锁就是 类锁：即当前类.class
     * 如果是非静态方法，同步锁是 this
     */
    public synchronized static void setTicket(){
        if(TicketCenter.restCount > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出了一张票，剩余："
                    + --TicketCenter.restCount + "张");
        }
    }
}
