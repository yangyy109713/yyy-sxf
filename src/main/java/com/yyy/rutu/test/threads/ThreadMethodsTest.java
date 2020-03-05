package com.yyy.rutu.test.threads;

public class ThreadMethodsTest {

    public static void main(String[] args){
        // 先实例化线程对象，再调用 setName 方法
        Thread t = new Thread();
        t.setName("Yangyy");
        System.out.println(t.getName());

        // 再实例化线程的同时，通过构造方法进行命名
        //Thread t1 = new Thread("Sxf");
        Thread t1 = new Thread(() -> {},"Sxy");//即 Thread(Runnable r, String name)
        System.out.println(t1.getName());

        // 使用自定义线程类时，在实例化线程对象时进行命名
        // 需要人为给自定义线程类添加构造方法
        MyThread2 mt = new MyThread2("mythread2");
        System.out.println(mt.getName());

    }
}

class MyThread2 extends Thread{

    public MyThread2(){}

    public MyThread2(String name){
        //super(name);
        this.setName(name);
    }
    // 将需要并发执行的任务逻辑写入 run 方法
    @Override
    public void run(){

    }
}
