package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用：若 Lambda 体中的内容有方法已经实现了，可以使用方法引用
 * 方法引用可以理解为 Lambda 表达式的另一种表现形式
 *
 * 主要语法格式：
 * 1、对象::实例方法名
 * 2、类::静态方法名
 * 3、类::实例方法名
 *
 * 注意：
 * Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 * 若 Lambda 参数列表中的第一个参数是 实例方法 的调用者，第二个参数是 实例方法 的参数时，可以使用 ClassName::method 的方式
 *
 * 构造器引用
 * 语法格式：ClassName::new
 * 注意：需要调用的构造器的参数列表要与函数式接口的抽象方法列表保持一致
 *
 * 数组引用
 * 语法格式：Type::new
 *
 */
public class MethodConstructor {

    //数组引用
    @Test
    public void test7(){
        Function<Integer, String[]> func = (x) -> new String[x];
        String[] strings = func.apply(10);
        System.out.println(strings.length);

        Function<Integer, String[]> func1 = String[]::new;
        String[] strings1 = func1.apply(12);
        System.out.println(strings1.length);

    }


    // 构造器引用
    @Test
    public void test5(){
        Supplier<Employee> sup = () -> new Employee();

        Supplier<Employee> sup1 = Employee::new; // 调用的构造器取决于函数式接口的参数个数
        System.out.println(sup1.get());// 打印结果：Employee{name='null', age=0, salary=0.0}
    }

    @Test
    public void test6(){
        Function<String, Employee> func = (name) -> new Employee(name);

        Function<String, Employee> func1 = Employee::new;
        System.out.println(func1.apply("na"));// 打印结果：Employee{name='na', age=0, salary=0.0}

        BiFunction<String, Integer, Employee> func2 = Employee::new;// 当没有对应参数个数的构造函数时，会报错
        System.out.println(func2.apply("Lily", 18));// 打印结果：Employee{name='Lily', age=18, salary=0.0}
    }


    // 类::实例方法名
    @Test
    public void test4(){
        // Lambda 参数列表中的第一个参数 x 是 equals() 的调用者
        // 第二个参数 y 是 equals() 的参数时，可以使用 ClassName::method 的方式
        BiPredicate<String, String> bi = (x, y) -> x.equals(y);

        BiPredicate<String, String> bi1 = String::equals;
    }

    // 类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compare;
    }

   // 对象::实例方法名
    @Test
    public void test1(){
        //Consumer<String> con = (e) -> System.out.println(e);

        PrintStream ps1 = System.out;
        Consumer<String> con = (e) -> ps1.println(e);

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;

        Consumer<String> con2 = System.out::println;
        con2.accept("abcdef");
    }

    @Test
    public void test2(){
        Employee employee = new Employee("yue", 12, 12.1);
        //Supplier<String> sup = () -> employee.getName();
        Supplier<String> sup = employee::getName;
        System.out.println(sup.get());

        //Supplier<Integer> sup1 = () -> employee.getAge();
        Supplier<Integer> sup1 = employee::getAge;
        System.out.println(sup1.get());
    }
}
