package com.yyy.rutu.test.juc;

import java.util.Random;
import java.util.concurrent.*;

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
public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws Exception{
        // 验证线程调度
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++){
            Future<Integer> result = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);//100以内的随机数
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    return num;
                }
            }, 1, TimeUnit.SECONDS);// 延迟3秒执行线程。TimeUnit 表示时间单位，包含 秒、小时、天 等
            System.out.println(result.get());
        }
        pool.shutdown();
    }
}
