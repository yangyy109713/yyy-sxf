package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * 常用方法：
 * Optional.of(T t) ：创建一个 Optional 实例
 * Optional.empty()：创建一个空的 Optional 实例
 * Optional.ofNullable(T t)：若 t 不为 null，创建 Optional 实例；否则创建空实例
 * isPresent()：判断是否包含值
 * orElse(T t)：如果调用对象包含值，返回该值；否则返回 t
 * orElseGet(Supplier s)：如果调用对象包含值，返回该值；否则返回 s
 * map(Function f)：如果有值对其处理，返回处理后的 Optional ；否则返回 Optional.empty()
 * flatMap(Function mapper)：与 map 类似，要求返回值必须是 Optional
 */

public class OptionalTest {

    // map(Function f)：如果有值对其处理，返回处理后的 Optional ；否则返回 Optional.empty()
    // flatMap(Function mapper)：与 map 类似，要求返回值必须是 Optional
    @Test
    public void test6(){
        Optional<Employee> op = Optional.ofNullable(new Employee("y1", 3, 1333.33, Employee.Status.FREE));
        Optional<Double> d =  op.map((e) -> e.getSalary());
        System.out.println(d.get());

        Optional<Double> d1 = op.flatMap((e) -> Optional.of(e.getSalary()));
        System.out.println(d1.get());
    }


    // orElseGet(Supplier s)：如果调用对象包含值，返回该值；否则返回 s
    @Test
    public void test5(){
        Optional op = Optional.ofNullable(null);
        Employee employee = (Employee) op.orElseGet(() -> new Employee());// 内部函数可以是其他逻辑，其他类型返回值
        Integer in = (Integer) op.orElseGet(() -> {
            return 100;
        });
        System.out.println(in);
    }

    // orElse(T t)：如果调用对象包含值，返回该值；否则返回 t
    @Test
    public void test4(){
        //Optional<Employee> op = Optional.ofNullable(new Employee());
        Optional<Employee> op = Optional.ofNullable(null);
        Employee emp = op.orElse(new Employee("y1", 3, 1333.33, Employee.Status.FREE));
        System.out.println(emp);
    }


    // Optional.ofNullable(T t)：若 t 不为 null，创建 Optional 实例；否则创建空实例
    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(new Employee());
        if(op.isPresent()){
            System.out.println(op.get());
        }else {
            System.out.println("对象为空！");
        }

        Optional<Employee> op1 = Optional.ofNullable(null);
        if(op1.isPresent()){
            System.out.println(op1.get());
        }else {
            System.out.println("对象为空！");
        }
        //System.out.println(op1.get());//java.util.NoSuchElementException: No value present
    }


    // Optional.empty()：创建一个空的 Optional 实例
    @Test
    public void test2(){
        Optional op = Optional.empty();
        System.out.println(op.get());//java.util.NoSuchElementException: No value present
    }

    // Optional.of(T t) ：创建一个 Optional 实例
    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee e = op.get();
        System.out.println(e);

        Optional<Employee> op1 = Optional.of(null);//会在该行报空指针；如果不用这种方式，没法明确知道哪里有异常
        System.out.println(op1.get());
    }
}
