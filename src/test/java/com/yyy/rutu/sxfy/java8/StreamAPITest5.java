package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPITest5 {

    List<Employee> employees = Arrays.asList(
            new Employee("y1", 23, 777.77, Employee.Status.FREE),
            new Employee("y2", 13, 888.88, Employee.Status.BUSY),
            new Employee("y3", 73, 19999.99, Employee.Status.VOCATION),
            new Employee("y4", 43, 9000.01, Employee.Status.BUSY),
            new Employee("y5", 53, 9000.02, Employee.Status.FREE)
    );

    /**
     * 收集
     * collect -- 将流转换为其他形式。接收一个 Collector 接口的实现，用于给 Stream 中元素做汇总
     * Collector 接口中方法的实现决定了如何对流执行收集操作（如收集到 List、Set、Map）
     * 但是 Collectors 实用类提供了很多静态方法，可以方便地创建常见的收集器实例
     */
    // 连接
    @Test
    public void testCollect6(){
       String str = employees.stream()
                .map(Employee::getName)
                //.collect(Collectors.joining()); // 无标点符号
                //.collect(Collectors.joining("，")); // 有标点符号
                .collect(Collectors.joining("，", "====", "====")); // 有标点符号和前后缀
       System.out.println(str);
    }

    // 组函数转换
    @Test
    public void testCollect5(){
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));// 获取关于工资的组函数：如总和、平均值、最大值等
        System.out.println(dss.getSum());
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());
    }

    // 分区
    @Test
    public void testCollect4(){
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(map);
    }

    // 多维分组
    @Test
    public void testCollect3(){
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,
                        Collectors.groupingBy((e) -> {
                            if(((Employee)e).getAge() < 35){
                                return "青年";
                            }
                            if(((Employee)e).getAge() < 50){
                                return "中年";
                            }else {
                                return "老年";
                            }
                        })));
        System.out.println(map);
    }

    // 分组：按照状态分组
    @Test
    public void testCollect2(){
        Map<Employee.Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    @Test
    public void testCollect1(){
        // 员工总数
        long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println(" ======== ");
        // 工资平均值
        Double avarage = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avarage);

        System.out.println(" ======== ");
        // 工资总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        System.out.println(" ======== ");
        // 工资最大值的员工
        Optional<Employee> op = employees.stream()
                .collect(Collectors
                        .maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(op.get());

        System.out.println(" ======== ");
        // 员工工资最小值
        Optional<Double> op1 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors
                        .minBy((e1, e2) -> Double.compare(e1, e2)));
        System.out.println(op1.get());
    }

    @Test
    public void testCollect(){
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());// 将员工姓名收集到 List 中
        list.forEach(System.out::println);

        System.out.println(" ======== ");
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());// 将员工姓名收集到 Set 中（过滤重复元素）
        set.forEach(System.out::println);

        System.out.println(" ======== ");
        // 对于特殊的数据结构，如 HashSet、LinkedList 等
        HashSet<String> hashSet = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));// 将员工姓名收集到 HashSet 中
        hashSet.forEach(System.out::println);
    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
     *  -- 将流中元素反复结合起来，得到一个值
     */
    @Test
    public void testReduce(){
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = nums.stream()
                .reduce(0, (x, y) -> x + y);// identity：起始值
        // 归约计算逻辑：将起始值0作为 x 值传入，从流中取出一个元素作为 y 值传入，计算出结果 x1
        // 再将 x1 作为 x 值传入，从流中取出下一个元素作为 y 值传入，结算出结果 x2；依次类推
        System.out.println(sum);

        Optional<Double> optional = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);// 结算结果可能为空，所以返回值是 Optional
        System.out.println(optional.orElse(null));
    }

    /**
     * 终止操作
     *  查找与匹配
     *    allMatch -- 检查是否匹配所有元素
     *    anyMatch -- 检查至少匹配一个元素
     *    noneMatch -- 检查是否没有匹配所有元素
     *    findFirst -- 返回第一个元素
     *    count -- 返回流中元素的个数
     *    max -- 返回流中最大值
     *    min -- 返回流中最小值
     */

    @Test
    public void testMatch(){
        long c = employees.stream().count();
        System.out.println(c);// 7

        // 查找工资最大的员工信息
        Optional<Employee> optional = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(optional.get());// Employee{name='y3', age=3, salary=1999.99, status=VOCATION}

        // 查找工资最小的 员工的工资
        Optional<Double> optional1 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(optional1.get());// 777.77
    }

    @Test
    public void testSearch(){
        boolean b1 = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);// false

        boolean b2 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);// true

        boolean b3 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);// false

        Optional<Employee> option = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(option.get());// Employee{name='y1', age=23, salary=777.77, status=FREE}

        //Optional<Employee> option1 = employees.stream() //串行流
        Optional<Employee> option1 = employees.parallelStream() // 并行流
                .filter((e1) -> e1.getStatus().equals(Employee.Status.BUSY))
                .findAny();
        System.out.println(option1.get());

    }

}
