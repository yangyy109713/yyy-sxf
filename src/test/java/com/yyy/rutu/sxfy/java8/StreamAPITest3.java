package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPITest3 {

    List<Employee> employees = Arrays.asList(
            new Employee("y1", 13, 777.77),
            new Employee("y2", 23, 888.88),
            new Employee("y3", 33, 1999.99),
            new Employee("y4", 43, 1000.01),
            new Employee("y5", 53, 1000.02),
            new Employee("y5", 53, 1000.02),
            new Employee("y5", 53, 1000.02)
    );

    List<String> list1 = Arrays.asList("aaa", "bbb", "ccc", "ddd");

    /**
     * 映射
     * map -- 接收 Lambda，将元素转换成其他形式或提取信息。（将一个个流加入到当前流中）
     *     -- 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap -- 接收一个函数作为参数，将流中的每一个值都换成另一个流，然后把所有流连接成一个流（将流中元素加入到当前流中）
     */
    @Test
    public void testMap(){
        list1.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println(" ============ ");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println(" ============ ");
        Stream<Stream<Character>> streamStream = list1.stream()
                .map((str) -> StreamAPITest3.toCharacter(str));// 返回的是多个流的集合：{{a,a,a},{b,b,b},...}
        streamStream.forEach((sm) ->
                sm.forEach(System.out::println));
    }

    @Test
    public void testFlatMap(){
        Stream<Character> sm = list1.stream()
                .flatMap(StreamAPITest3::toCharacter);// 返回的是一个流：{a,a,a,b,b,b,...}
        sm.forEach(System.out::println);

    }

    // 比较 List.add(Object e)（将集合加入到当前集合中）
    //     与 List.addAll(Collection c)（将集合中元素加入到当前集合中）
    @Test
    public void testListAdd(){
        List list2 = new ArrayList();
        list2.add(11);
        list2.add(12);
        list2.add(list1);
        //list2.addAll(list1);
        System.out.println(list2);
    }

    private static Stream<Character> toCharacter(String str){
        List<Character> list = new ArrayList<>();
        for(Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }
}
