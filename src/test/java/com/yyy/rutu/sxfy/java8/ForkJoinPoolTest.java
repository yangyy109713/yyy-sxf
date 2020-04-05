package com.yyy.rutu.sxfy.java8;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join 框架
 *         即在必要的情况下，将一个大任务，进行拆分（fork）成若干个小任务（拆分到不可拆分时），再将一个个小任务的运算结果进行 join 操汇总。
 *
 *         Fork/Join 框架 与线程池的区别
 *         采用“工作窃取“模式：
 *         当执行新任务时可以将拆分成更小的任务执行，并将小任务加到线程队列中，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。
 *         相对于一般线程池实现，fork/join 框架的优势体现在对其中包含的任务处理方式上。在一般线程池中，如果一个线程正在执行的任务由于某些原因无法继续运行，那么该线程会处于等待状态。在 fork/join 框架中，如果某个子问题由于等待另一个子问题的完成而无法继续运行，那么处理该问题的子线程会主动寻找其他尚未运行的子问题来执行（一般从线程池末尾），减少了线程的等待时间，提高了性能。
 *
 *         Fork/Join 框架的示例代码：
 */

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
    // 与Java for 循环以及 Java 8 新特性比较，具体见 TestForkJoin.java
}

// RecursiveAction 无返回值
// RecursiveTask 有返回值
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
