package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPITest1 {

    // 创建流
    @Test
    public void streamTest1(){
        // 1.通过 Collection 系列集合提供的 .stream()（串行流） 或 .parallelStream()（并行流）
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();//list.parallelStream();

        // 2.通过 Arrays 中的静态方法 stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(emps);

        // 3.通过 Stream 类的静态方法 of()
        Stream<String> stream2 = Stream.of("aaa", "bbb", "yyy");

        // 4.创建 无限流
        // 迭代：按照 一元运算的规则产生
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);// 创建流
        //stream3.forEach(System.out::println);
        stream3.limit(12)   // 中间操作
                .forEach(System.out::println);// 终止操作

        // 生成
        Stream.generate(() -> Math.random() * 10)
                .limit(5)
                .forEach(System.out::println);
    }
}
