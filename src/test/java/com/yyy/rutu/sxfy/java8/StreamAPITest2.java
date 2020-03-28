package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPITest2 {

    List<Employee> list = Arrays.asList(
            new Employee("y1", 13, 777.77),
            new Employee("y2", 23, 888.88),
            new Employee("y3", 33, 1999.99),
            new Employee("y4", 43, 1000.01),
            new Employee("y5", 53, 1000.02),
            new Employee("y5", 53, 1000.02),
            new Employee("y5", 53, 1000.02)
    );

    /**
     * 筛选与切片
     * filter -- 接收 Lambda，从流中排除某些元素
     * limit -- 截断流，使其元素不超过给定数量
     * skip -- 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit 互补
     * distinct -- 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */

    @Test
    public void testDistinct(){
        list.stream()
                .filter((emp) -> emp.getAge() > 20)
                .distinct() // 去重：需要保证集合对象已重写 hashCode 和 equals 方法
                .forEach(System.out::println);
    }

    @Test
    public void testSkip(){
        list.stream()
                .filter((emp) -> emp.getAge() > 20)
                .skip(2) // 跳过前 2 个
                .forEach(System.out::println);
    }

    @Test
    public void testLimit(){
        list.stream()
                .filter((emp) -> emp.getAge() > 20)
                .limit(2)
                .forEach(System.out::println);
    }


    // 内部迭代：迭代操作由 Stream API 完成
    @Test
    public void testFilter(){
        Stream<Employee> stream = list.stream();
        // 中间操作：不会执行任何动作
        //stream.filter((emp) -> emp.getAge() > 20)
        stream.filter((emp) -> {
            System.out.println("Test Filter!");
            return emp.getAge() > 20;
        })
                .forEach(System.out::println);// 终止操作：一次性执行全部内容，即"惰性求值"


    }

    // 外部迭代
    @Test
    public void testFilter1(){
        Iterator<Employee> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
