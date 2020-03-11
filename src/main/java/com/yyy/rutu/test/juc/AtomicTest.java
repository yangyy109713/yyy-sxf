package com.yyy.rutu.test.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * i++ 的原子性问题：i++ 操作实际上分为三个步骤"读-改-写"
 *      int i = 10; i = i++; // i = 10
 * 底层相当于：int temp = i ; i = i +1; i = temp;
 *
 * 解决：使用原子变量
 * jdk 1.5 以后，Java 提供了 java.util.concurrent.atomic 包，该包下提供了大量常用的原子变量
 * 原子变量包含：
 * 1. volatile 关键字：保证内存可见性
 * 2. CAS（Compare-And-Swap）算法：保证数据的原子性
 *  CAS 算法：
 *    硬件对于并发操作共享数据的底层支持
 *    包含了三个操作数据：内存值 V、 预估值 A、更新值 B
 *    当且仅当 A == V 时，才执行 V = B；否则不做任何操作
 *
 */
public class AtomicTest {
    AtomicInteger atomicInteger;

    public static void main (String[] args){
        AtomicDemo demo = new AtomicDemo();
        // 创建10个线程，同时执行
        for(int i = 0; i < 10; i++){
            new Thread(demo).start();
        }
    }
}

class AtomicDemo implements Runnable{
    // private volatile int serialNum = 0;
    // AtomicInteger 底层已经使用了 volatile 关键字
    private AtomicInteger serialNum = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + getSerialNum());
    }

    public int getSerialNum() {
        return serialNum.getAndIncrement();// 相当于 i++
        /**
         * 补充：
         * serialNum.compareAndSet();// CAS 算法
         * serialNum.getAndIncrement();// 相当于 i++
         * serialNum.incrementAndGet(); // 相当于 ++i
         * serialNum.getAndDecrement(); // 相当于 ++i
         * serialNum.decrementAndGet();// 相当于 --i
         */
    }
}