package com.yyy.rutu.test.jvm;

public class DeadThreadTest {

    public static void main(String[] args){
        Runnable r = () -> {
          System.out.println(Thread.currentThread().getName() + "开始");
          DeadThread deadThread = new DeadThread();
          System.out.println(Thread.currentThread().getName() + "结束");
        };

        Thread t1 = new Thread(r, "线程1");
        Thread t2 = new Thread(r, "线程2");

        t1.start();
        t2.start();
        /**
         * 线程1，线程2 是并行执行，都要初始化 DeadThread 类
         * 但是 DeadThread 只能被初始化一次
         * 只会有一个线程进入 DeadThread 的static，打印测试语句
         */
    }
}

class DeadThread{
    static {
        if(true){
            System.out.println(Thread.currentThread().getName() + "初始化当前类");
            while (true){

            }
        }
    }
}
