package com.yyy.rutu.test.threads;

public class ThreadPriorityTest {
    public static void main(String[] args){
        priorityTest();
    }

    public static void priorityTest(){
        // 设置线程优先级，只是修改线程可以抢到CPU时间片的概率，并不是优先级高的线程一定能抢到CPU时间片
        // 优先级的设置参数，是一个整数[0,10]，默认是 5
        Runnable r = () -> {
          for (int i = 0; i < 10; i++){
              System.out.println(Thread.currentThread().getName() + "：" + i);
          }
        };
        Thread t1 = new Thread(r, "thread-t1");
        Thread t2 = new Thread(r, "thread-t2");
        //设置线程优先级：必须放在线程开始执行前（即 start 方法之前）
        t1.start();
        t2.start();
    }
}
