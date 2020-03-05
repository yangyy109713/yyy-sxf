package com.yyy.rutu.test.threads;

public class ThreadYieldTest {

    public static void main(String[] args){
        yieldTest();
    }

    // 线程的礼让
    public static void yieldTest(){
        // 线程 Ready、Run ：线程抢到了CPU时间片，有Ready进入Run状态；同时可以由Run状态回到Ready状态：通过yield
        // 什么叫线程的礼让？让当前运行中的线程放弃CPU资源，由运行状态回到就绪状态
        Runnable r = () -> {
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + "：" + i);
                if(i == 3){
                    Thread.yield();//线程礼让
                }
            }
        };
        Thread t1 = new Thread(r, "thread-t1");
        Thread t2 = new Thread(r, "thread-t2");
        t1.start();
        t2.start();
    }
}
