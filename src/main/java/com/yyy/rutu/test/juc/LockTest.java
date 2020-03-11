package com.yyy.rutu.test.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决对线程安全问题的方式：
 * 关键字 synchronized，隐式锁（JVM 底层关键字）
 * 1. 同步代码块
 * 2. 同步方法
 *
 * jdk 1.5 以后
 * 3. 同步锁：Lock
 * 注意：Lock 是一个显示锁，需要通过 lock() 上锁，unlock() 释放锁（通常放在 finally 中，保证释放锁）
 * Lock 是一个接口，需要通过实现类创建对象，ReentrantLock
 * 示例代码：模拟多个窗口同时售票，余票数
 */
public class LockTest {
    public static void main(String[] args){
        LockTicketDemo ticketDemo = new LockTicketDemo();
        new Thread(ticketDemo, "1号窗口").start();
        new Thread(ticketDemo, "2号窗口").start();
        new Thread(ticketDemo, "3号窗口").start();
    }
}

class LockTicketDemo implements Runnable{

    private int ticket = 50;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            lock.lock();// 给资源上锁
            try {
                // 将多线程安全问题放大
                if(ticket > 0){
                    try {
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "剩余票数：" + --ticket);
                }
            }finally {
                lock.unlock();// 释放锁
            }
        }
    }
}