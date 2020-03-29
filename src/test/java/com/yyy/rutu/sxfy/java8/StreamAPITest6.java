package com.yyy.rutu.sxfy.java8;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamAPITest6 {
    /**
     * Stream API 练习
     */
    // 1：给定一个数字列表，返回由每个数的平方组成的列表
    @Test
    public void test1(){
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
        Arrays.stream(nums)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }

    // 2：怎么用 map 和 reduce 计算流中有多少个 Employee
    List<Employee> employees = Arrays.asList(
            new Employee("y1", 23, 777.77, Employee.Status.FREE),
            new Employee("y2", 13, 888.88, Employee.Status.BUSY),
            new Employee("y3", 73, 19999.99, Employee.Status.VOCATION),
            new Employee("y4", 43, 9000.01, Employee.Status.BUSY),
            new Employee("y5", 53, 9000.02, Employee.Status.FREE)
    );

    @Test
    public void test2(){
        Optional<Integer> optional = employees.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(optional.get());
    }

    List<Transaction> transactions ;
    @Before
    public void before(){
        Trader t1 = new Trader("Lily", "BeiJing");
        Trader t2 = new Trader("Mei", "ShangHai");
        Trader t3 = new Trader("Yan", "HangZhou");
        Trader t4 = new Trader("Ad", "HangZhou");

        transactions = Arrays.asList(
                new Transaction(t1, 2001, 1000),
                new Transaction(t2, 2011, 2000),
                new Transaction(t2, 2012, 900),
                new Transaction(t3, 2011, 1500),
                new Transaction(t3, 2011, 1400),
                new Transaction(t4, 2020, 2500)
        );
    }
    // 1.找出 2011 年发生的所有交易，并按交易额从高到低排序
    @Test
    public void test3(){
        transactions.stream()
                .filter((t) -> t.getYear() == 2011)
                .sorted((t1, t2) -> -Integer.compare(t1.getValue(), t2.getValue()))
                .forEach(System.out::println);
    }

    // 2.交易员在哪些不同的城市工作
    @Test
    public void test4(){
        transactions.stream()
                .map((e) -> e.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    // 3.查找所有来自 HangZhou 的交易员，并按姓名排序
    @Test
    public void test5(){
        transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("HangZhou"))
                .map(Transaction::getTrader)
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .forEach(System.out::println);
    }

    // 4.返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test6(){
        transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted((n1, n2) -> n1.compareTo(n2))
                //.distinct() // 去重
                .forEach(System.out::println);

        System.out.println(" ---------------------- ");
        String str = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("HangZhou"))
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()// 先按姓名排序，再拼接
                .reduce("", String::concat);
        System.out.println(str);

        System.out.println(" ---------------------- ");
        transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("HangZhou"))
                .map((t) -> t.getTrader().getName())
                //.flatMap(StreamAPITest6::getCharacterStream1)
                //.sorted()
                .flatMap(StreamAPITest6::getCharacterStream)
                .sorted((s1, s2) -> s1.compareToIgnoreCase(s2)) // 排序时忽略大小写
                .forEach(System.out::print);
    }
    private static Stream<String> getCharacterStream(String str){
        List<String> characters = new ArrayList<>();
        for (Character ch : str.toCharArray()){
            characters.add(ch.toString());
        }
        return characters.stream();
    }
    private static Stream<Character> getCharacterStream1(String str){
        List<Character> characters = new ArrayList<>();
        for (Character ch : str.toCharArray()){
            characters.add(ch);
        }
        return characters.stream();
    }

    // 5.有没有交易员是在米兰工作过
    @Test
    public void test7(){
        Boolean b = transactions.stream()
                .anyMatch((t) -> t.getTrader().getCity().equals("米兰"));
        System.out.println(b);
    }

    // 6.打印所有的来自 HangZhou 交易员的总交易额
    @Test
    public void test8(){
        Optional<Integer> sum = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("HangZhou"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(sum.get());
    }

    // 7.所有交易中，最高的交易额是多少
    @Test
    public void test9(){
        Optional<Integer> v = transactions.stream()
                .map(Transaction::getValue)
                //.max((v1, v2) -> v1.compareTo(v2));
                .max(Integer::compare);
        System.out.println(v.get());
    }

    // 8.找到交易额最小的交易
    @Test
    public void test10(){
        Optional<Integer> v = transactions.stream()
                .map(Transaction::getValue)
                //.collect(Collectors.minBy((v1, v2) -> v1.compareTo(v2)));
                .min((t1, t2) -> t1.compareTo(t2));
        System.out.println(v.get());
    }

}
