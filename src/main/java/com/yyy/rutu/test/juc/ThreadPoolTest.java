package com.yyy.rutu.test.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池：提供一个线程队列，队列中保存所有等待状态的线程，避免了创建与销毁线程时的额外开销，提高相应速度。
 * 线程池的体系结构：
 *  java.util.concurrent.Executor：负责线程使用与调度的根接口
 *  |--ExecutorService：子接口，线程池的主要接口
 *     |--ThreadPoolExecutor：线程池的实现类
 *     |--ScheduledExecutorService：子接口，负责线程的调度
 *         |--ScheduledThreadPoolExecutor：继承 ThreadPoolExecutor，实现 ScheduledExecutorService
 * 工具类：Executors
 * ExecutorService newFixedThreadPool()：创建固定大小的线程池
 * ExecutorService newCachedThreadPool()：缓存线程池，线程池的数量不固定，可以根据需求自动更改数量
 * ExecutorService newSingleThreadPool()：创建单个线程池，线程池中只有一个线程
 *
 * ScheduledExecutorService newScheduledThreadPool()：创建固定大小的线程池，可以延时/定时执行任务
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws Exception{
        /*
        // 以往创建线程的方式
        ThreadPoolDemo threadDemo = new ThreadPoolDemo();
        new Thread(threadDemo).start();// 总是遵循"创建-使用-销毁"的流程：频繁创建和销毁线程，非常耗费 CPU 资源
        */

        /*// 线程池的使用
        ThreadPoolDemo threadDemo = new ThreadPoolDemo();
        // 1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);// 固定大小为5的线程池，即内部有5个线程
        // 2.为线程池中的线程分配任务
        //pool.submit(threadDemo);//分配一个任务
        for(int i = 0; i < 10; i++){
            //分配10个任务
            pool.submit(threadDemo);
        }
        // 3.关闭线程池
        pool.shutdown();*/

        // 使用 Callable 方式实现
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Future<Integer> future = pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 0; i <= 100; i++){
                        sum += i;
                    }
                    return sum;
                }
            });
            list.add(future);
        }

        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).get());
        }
        pool.shutdown();

    }
}

class ThreadPoolDemo implements Runnable{
    @Override
    public void run() {
        for(int i = 1; i <= 20; i++){
            System.out.println(Thread.currentThread().getName() + "：" + i);
        }
    }
}