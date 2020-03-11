package com.yyy.rutu.test.juc;

/**
 * volatile 关键字：保证多个线程访问共享数据时，彼此的操作在内存中是可见的。
 * 调用的 Java 底层内存栅栏，可以保证内存数据实时刷新。相当于线程直接操作主存中的数据。
 * 效率有降低，但比锁效率高。效率降低原因：JVM 底层的优化--重排序，使用 volatile 之后无法重排序。
 * 相较于 synchronized 是一种较为轻量级的同步策略。
 * 注意：
 * 1. volatile 不具备"互斥性"
 * 2. volatile 不能保证变量的"原子性"（原子：不可再分）
 */
public class VolatileTest {
    public static void main(String[] args){
        // 子线程开始：目的是给子线程的 flag 改值
        ThreadDemo demo = new ThreadDemo();
        new Thread(demo).start();

        // 主线程开始：目的是读子线程的 flag 值
        while (true){
            if(demo.isFlag()){
                System.out.println("------------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable{

    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag = " +  isFlag());
    }
}

// 运行测试代码："------"无法打印，且主线程无法结束