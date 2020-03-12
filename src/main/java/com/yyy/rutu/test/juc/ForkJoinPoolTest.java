package com.yyy.rutu.test.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {
    public static void main(String[] args){
        Instant start = Instant.now();

        // 验证拆分逻辑，ForkJoin 需要使用 ForkJoinPool 实现
        ForkJoinPool pool = new ForkJoinPool();
        // 分配任务
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 50000000000L);
        long sum = pool.invoke(task);
        System.out.println("计算结果："+ sum);

        Instant end = Instant.now();
        System.out.println("耗费时间："+ Duration.between(start, end).toMillis());
    }

    // 与Java for 循环以及 Java 8 新特性比较，具体见 test 包下 TestForkJoin.java

}

class ForkJoinSumCalculate extends RecursiveTask<Long>{
    private static final long serialVersionUID = -259195479995561737L;

    private long start;
    private long end;

    private static final long THURSHOLD = 10000L;//临界值

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THURSHOLD){
            long sum = 0L;
            for(long i = start; i <= end; i++){
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) / 2;//作为拆分中间数
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();//执行拆分,同时压入线程队列

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();// 进行合并，并返回
        }
    }
}
