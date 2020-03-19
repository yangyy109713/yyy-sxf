package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.*;

public class LambdaTest {

    // 匿名内部类
    @Test
    public void old(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);//比较两个数值大小：0 相等；负 o1 小于 o2；正  o1 大于 o2
            }
        };
        // 把 comparator 作为一个参数使用
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(4);
        ascIteratorTreeSet(treeSet);
    }

    // Lambda 表达式
    @Test
    public void lambdaTest(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        // 把 comparator 作为一个参数使用
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(4);
        descIteratorTreeSet(treeSet);
    }

    //顺序遍历 TreeSet
    private static void ascIteratorTreeSet(TreeSet<Integer> treeSet){
        for (Iterator iter = treeSet.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
    }

    //逆序遍历 TreeSet
    private static void descIteratorTreeSet(TreeSet<Integer> treeSet){
        for (Iterator iter = treeSet.descendingIterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
    }


    List<Employee> list = Arrays.asList(
            new Employee("y1", 13, 777.77),
            new Employee("y2", 23, 888.88),
            new Employee("y3", 33, 1999.99),
            new Employee("y4", 43, 1000.01)
    );

    // 优化方式一：策略设计模式，实现按条件过滤员工
    @Test
    public void filterEmployee1(){
        // 过滤年龄大于30的员工
        List<Employee> ls = filter(list, new FilterEmployeeByAge());
        for (Employee emp : ls){
            System.out.println(emp);
        }

        System.out.println("===========");

        // 过滤工资大于800的员工
        List<Employee> ls1 = filter(list, new FilterEmployeeBySalary());
        for (Employee emp : ls1){
            System.out.println(emp);
        }
    }

    // 优化方式二：匿名内部类
    @Test
    public void filterEmployee2(){
        List<Employee> list1 = filter(list, new MyPredicate<Employee>() {
            @Override
            public boolean compare(Employee employee) {
                return employee.getSalary() > 800;
            }
        });
        // 过滤工资大于800的员工
        for (Employee emp : list1){
            System.out.println(emp);
        }
    }

    // 优化方式三：Lambda 表达式
    @Test
    public void filterEmployee3(){
        List<Employee> list1 = filter(list, (e) -> e.getSalary() > 900);
        // 过滤工资大于900的员工
        list1.forEach(System.out::println);
    }

    private List<Employee> filter(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> list1 = new ArrayList<>();
        for (Employee employee : list){
            if (mp.compare(employee)){
                list1.add(employee);
            }
        }
        return list1;
    }


    // 优化方式四：Stream API
    @Test
    public void filterEmployee4(){
        list.stream()
                .filter(e -> e.getSalary() > 1000)
                .forEach(System.out::println);

        System.out.println("----------");

        list.stream()
                //.map(e -> e.getName()) //和下面代码作用一样
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
