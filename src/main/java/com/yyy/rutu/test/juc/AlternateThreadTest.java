package com.yyy.rutu.test.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启三个线程，三个线程 ID 分别为A、B、C
 * 每个线程将自己的 ID 打印10遍，要求输出结果按顺序显示，如：ABC ABC ABC ...，依次递归
 */
public class AlternateThreadTest {
    public static void main(String[] args){
        AlternateDemo demo = new AlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.loopA(10);
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.loopB(10);
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.loopC(10);
            }
        }, "C").start();
    }
}

class AlternateDemo {
    private int number = 1;

    private Lock lock = new ReentrantLock();

    private Condition cond1 = lock.newCondition();
    private Condition cond2 = lock.newCondition();
    private Condition cond3 = lock.newCondition();

    public void loopA(int totalLoop){
        lock.lock();
        try {
            for (int i = 1; i <= totalLoop; i ++){
                // 1.判断是第几个线程要执行：1表示第一个线程
                if(number != 1){
                    cond1.await();
                }
                // 2.执行打印
                System.out.println(Thread.currentThread().getName() + "执行第 " + i + " 次");
                // 3.唤醒其他线程
                number = 2;
                // 每次唤醒一个线程
                cond2.signal();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(int totalLoop){
        lock.lock();
        try {
            for(int i = 1; i <= totalLoop; i ++){
                if(number != 2){
                    cond2.await();
                }
                System.out.println(Thread.currentThread().getName() + " 执行第 " + i + " 次");
                number = 3;
                cond3.signal();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopC(int totalLoop){
        lock.lock();
        try {
            for (int i = 1; i <= totalLoop; i ++){
                if(number != 3){
                    cond3.await();
                }
                System.out.println(Thread.currentThread().getName() + "执行第 " + i + " 次");
                number = 1;
                cond1.signal();
                System.out.println("-------------------");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
