package com.yyy.rutu.test.threads;

public class ThreadInitializeTest {

    public static void main(String[] args){
        // 线程实例化两种常见方式
        // 1.自定义线程类：继承自 Thread 类，作为线程子类，并重写 run 方法
        MyThread mt = new MyThread();// 实例化后，mt 为 新生态

        // 注意：启动线程需要调用 start 方法：开启一个新的线程 mt，执行 run 中的逻辑
        // 如果直接调用 run 方法，则线程 mt 不会进入 就绪态：
        // 即此刻并没有进行线程并发，run 中的所有逻辑还是在当前调用 run 的线程中执行
        mt.start();// 开启线程，mt 进入 就绪态

        // 2.通过 Runnable 接口
        /*Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };*/
        // 或者使用 Lambda 表达式
        Runnable r1 = () -> {
            for(int i = 0; i < 10; i++){
                System.out.println("子线程 r1 逻辑：" + i);
            }
        };
        Thread t = new Thread(r1);
        t.start();

        System.out.println("主线程逻辑结束了。。。");
    }
}

class MyThread extends Thread{

    // 将需要并发执行的任务逻辑写入 run 方法
    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("子线程逻辑：" + i);
        }
    }
}
