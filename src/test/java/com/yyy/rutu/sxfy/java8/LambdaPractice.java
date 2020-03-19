package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaPractice {

    List<Employee> list = Arrays.asList(
            new Employee("y12", 13, 777.77),
            new Employee("y1", 13, 888.88),
            new Employee("y3", 33, 1999.99),
            new Employee("y14", 43, 1000.01)
    );

    @Test
    public void test1(){
        Collections.sort(list, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                //return Integer.compare(e1.getAge(), e2.getAge());//按年龄正序排
                return - Integer.compare(e1.getAge(), e2.getAge());//按年龄倒序排
            }
        });

        for(Employee employee : list){
            System.out.println(employee);
        }
    }


    @Test
    public void test2(){
        String s1 = "yyy-sxf";
        System.out.println(strHandler(s1, (e) -> e.toUpperCase()));// YYY-SXF
        System.out.println(strHandler(s1, (e) -> e.substring(2,4)));// y-
    }

    private String strHandler(String str, MyPractice mp){
        return mp.getValue(str);
    }


    @Test
    public void test3(){
        Long l1 = Long.valueOf("10");
        Long l2 = Long.valueOf("12");
        System.out.println(longHandler(l1, l2, (x, y) -> (x + y)));
        System.out.println(longHandler(l1, l2, (x, y) -> (x * y)));
    }

    private Long longHandler(Long l1, Long l2, MyPractice1<Long, Long> mp){
        return mp.getValue(l1, l2);
    }
}
