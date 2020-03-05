package com.yyy.rutu.test.threads;

public class DeadLockTest {
    public static void main(String[] args){
        // 死锁：多个对象彼此持有对方所需要的锁对象，而不释放自己的锁
        Runnable r1 = () -> {
            synchronized ("A"){
                System.out.println("A线程持有了A锁，等待B锁");
                synchronized ("B"){
                    System.out.println("A线程同时持有了A锁和B锁");
                }
            }
        };

        Runnable r2 = () -> {
            synchronized ("B"){
                System.out.println("B线程持有了B锁，等待A锁");
                synchronized ("A"){
                    System.out.println("A线程同时持有了A锁和B锁");
                }
            }
        };

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);

        thread1.start();
        thread2.start();
    }
}
