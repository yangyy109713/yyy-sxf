package com.yyy.rutu.sxfy;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

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
}
