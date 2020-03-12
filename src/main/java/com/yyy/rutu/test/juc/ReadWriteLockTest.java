package com.yyy.rutu.test.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：ReadWriteLock
 * 读操作：
 * 写操作：
 */
public class ReadWriteLockTest {
    public static void main(String[] args){
        ReadWriteLockDemo writeLockDemo = new ReadWriteLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                writeLockDemo.setN((int)(Math.random() * 101));
            }
        }, "写：  ·").start();

        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    writeLockDemo.getN();
                }
            }, "读").start();

        }
    }
}

class ReadWriteLockDemo{
    private int n = 0;//共享资源

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void getN(){
        lock.readLock().lock();//读操作-上锁

        try {
            System.out.println(Thread.currentThread().getName() + " : " + n);
        }finally {
            lock.readLock().unlock();//释放锁
        }
    }

    public void setN(int n){
        lock.writeLock().lock();//写操作-上锁

        try {
            System.out.println(Thread.currentThread().getName());
            this.n = n;
        }finally {
            lock.writeLock().unlock();//释放锁
        }
    }
}