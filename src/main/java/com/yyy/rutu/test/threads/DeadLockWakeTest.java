package com.yyy.rutu.test.threads;

public class DeadLockWakeTest {
    public static void main(String[] args){
        /**
         * 解决死锁的办法
         * 使用 Object类中的方法
         * wait：等待，让当前的线程释放自己的锁标记，并且让出 CPU 资源。使得当前线程进入等待队列
         * notify：通知，唤醒等待队列中的一个线程，并使被唤醒的这个线程进入锁池
         * notifyAll：通知，唤醒等待队列中的所有线程，并使这些线程进入锁池
         */

        // 死锁：多个对象彼此持有对方所需要的锁对象，而不释放自己的锁
        Runnable r1 = () -> {
            synchronized ("A"){
                System.out.println("A线程持有了A锁，等待B锁");
                try {
                    "A".wait();//等待，让当前的线程释放自己的锁标记
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
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
                    "A".notifyAll();//唤醒等待队列中的线程：如果不加这句话，A会一直等待，且自己无法主动唤醒，仍然会有死锁存在
                }
            }
        };

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);

        thread1.start();
        thread2.start();
    }
}
