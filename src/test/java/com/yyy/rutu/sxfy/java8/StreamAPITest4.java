package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StreamAPITest4 {

    List<Employee> employees = Arrays.asList(
            new Employee("y1", 23, 777.77),
            new Employee("y2", 13, 888.88),
            new Employee("y3", 3, 1999.99),
            new Employee("y4", 43, 1000.01),
            new Employee("y25", 53, 1000.02),
            new Employee("y15", 53, 1000.02)
    );

    List<String> list = Arrays.asList("eee", "ddd", "bbb", "ccc", "aaa");

    /**
     * 排序
     * sorted -- 自然排序（Comparable，即按照 compareTo 方式排序）
     * sorted(Comparator com) -- 定制排序
     */
    @Test
    public void testSorted(){
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println(" ========= ");
        employees.stream()
                .sorted((e1, e2) -> {
                    if(e1.getAge() == e2.getAge()){
                       return e1.getName().compareTo(e2.getName());
                    }else {
                       return -e1.getAge().compareTo(e2.getAge());// 返回负数表示倒序
                    }
                }).forEach(System.out::println);
    }

    @Test
    public void testSorted1(){

    }
}
