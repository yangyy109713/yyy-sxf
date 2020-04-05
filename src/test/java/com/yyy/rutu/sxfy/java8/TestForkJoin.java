package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;


//与Java for 循环以及 Java 8 新特性：
public class TestForkJoin {
    @Test
    public void test1(){
        Instant start = Instant.now();

        long sum = 0L;
        for (long i = 0L; i < 10000000000L; i++){
            sum += i;
        }
        System.out.println("计算结果："+ sum);

        Instant end = Instant.now();
        System.out.println("耗费时间："+ Duration.between(start, end).toMillis());
    }

    // java 8 并行流
    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0L, 50000000000L)
                .parallel() // 并行操作
                .reduce(0L, Long::sum);
        System.out.println("计算结果："+ sum);

        Instant end = Instant.now();
        System.out.println("耗费时间："+ Duration.between(start, end).toMillis());
    }

    // java 8 串行流
    @Test
    public void test3(){
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0L, 50000000000L)
                .sequential() // 串行操作
                .reduce(0L, Long::sum);
        System.out.println("计算结果："+ sum);

        Instant end = Instant.now();
        System.out.println("耗费时间："+ Duration.between(start, end).toMillis());
    }
}


//Ps：当数据足够大，如最大为500亿时（50000000000L），和 Java 8 新特性差不多，但 CPU 使用率稍微低一点儿