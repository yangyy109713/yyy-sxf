package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Function4Test {

    // Consumer<T> 消费型接口
    @Test
    public void test1(){
        Double d = 1000.01;
        happy(d, (m) -> System.out.println("吃饭，花了 " + m + "元"));
    }
    private void happy(Double d, Consumer<Double> con){
        con.accept(d);
    }


    // Supplier<T> 供给型接口
    @Test
    public void test2(){
        // 每次产生一个随机数放入集合中，共10次
        List<Integer> list = supply(10, () -> (int)(Math.random() * 100));
        for(Integer num : list){
            System.out.println(num);
        }
    }
    // 产生制定个数的整数，并放入集合中
    private List<Integer> supply(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < num; i++){
            list.add(sup.get());
        }
        return list;
    }

    // Function<T, R> 函数型接口
    @Test
    public void test3(){
        String str = "\t 大中国一样一样呀！  ";
        System.out.println(function(str, (e) -> e.trim()));
        System.out.println(function(str, (e) -> e.substring(2,5)));
    }

    private String function(String str, Function<String, String> func){
        return func.apply(str);
    }


    // Predicate<T> 断定型接口
    @Test
    public void test4(){
        List<String> strs = Arrays.asList("Hello", "China", "xi", "Hangzhou");
        //List<String> newStrs = predicate(strs, (str) -> str.length() > 3);
        List<String> newStrs = predicate(strs, (str) -> str.startsWith("H"));
        for (String s : newStrs){
            System.out.println(s);
        }
    }

    // 将满足条件的字符串，放入集合中
    private List<String> predicate(List<String> list, Predicate<String> p){
        List<String> strList = new ArrayList<>();
        for (String str : list){
            if(p.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }
}
