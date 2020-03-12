package com.yyy.rutu.test.juc;

/**
 * 判断打印的是 one，还是 two
 * 1. 两个普通同步方法，两个线程 --> 标准打印：打印结果是：one two
 * 2. 新增 Thread.sleep() 给 getOne() --> 打印结果：等3秒后，one two
 * 3. 新增普通方法 getThree()，第3个线程调用 --> 打印结果：three，等3秒后，one two
 * 4. 两个普通同步方法，两个 Thread8LockDemo 对象，两个线程 --> 打印结果：two，等3秒后，one
 * 5. getOne() 改为 static 静态方法 --> 打印结果：two，等3秒后，one
 * 6. getOne() 和 getTwo() 都改成 static 静态方法 --> 打印结果：等3秒后，one two
 * 7. getOne()为静态同步方法，getTwo()非静态同步方法，两个 Thread8LockDemo 对象，两个线程 --> 打印结果：two，等3秒后，one
 * 8. 都为静态方法，两个 Thread8LockDemo 对象，两个线程 --> 打印结果：等3秒后，one two
 *
 * 总结：
 * ==> 非静态锁是 this，静态锁是当前 Class 对象，即 Thread8LockDemo
 * 同一时刻，只有一个线程能持有锁，无论几个方法，其他线程必须等待
 */
public class Thread8LockTest {

    public static void main(String[] args){
        Thread8LockDemo lockDemo1 = new Thread8LockDemo();
        Thread8LockDemo lockDemo2 = new Thread8LockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lockDemo1.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lockDemo2.getTwo();
                //lockDemo1.getTwo();
            }
        }).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                lockDemo.getThree();
            }
        }).start();*/
    }
}

class Thread8LockDemo{

    public static synchronized void getOne(){
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){}
        System.out.println("one");
    }

    public static synchronized void getTwo(){
        System.out.println("two");
    }

    public void getThree(){
        System.out.println("three");
    }
}