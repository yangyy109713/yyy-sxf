package com.yyy.rutu.test.threads;

public class ThreadSleepTest {

    public static void main(String[] args){
        sleepTest();
    }

    public static void sleepTest(){
        // 实例化线程
        MyThread3 mt3 = new MyThread3();
        mt3.start();//启动线程，会进入就绪态（去争抢CPU时间片）
    }
}

class MyThread3 extends Thread{

    public MyThread3(){}

    public MyThread3(String name){
        //super(name);
        this.setName(name);
    }
    // 将线程休眠逻辑写入 run 方法
    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println(i);
            // 线程休眠:参数是以毫秒为单位
            // 线程休眠：会由运行态进入阻塞态；待休眠时间到了以后，由阻塞态进入就绪态（可以去争抢CPU时间片），
            // 若在就绪态抢到了CPU时间片，会再次进入运行态
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
