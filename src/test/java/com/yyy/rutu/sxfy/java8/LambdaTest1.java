package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Lambda 表达式的基本语法：
 * Java 8 中引入了一个新的操作符"->"，该操作符称为 箭头操作符 或 Lambda 操作符
 * "->"将 Lambda 表达式拆分成了两部分：左侧 & 右侧
 * 左侧：Lambda 表达式所需要的参数
 * 右侧：Lambda 表达式中所需执行的功能，即 Lambda 体
 *
 * Lambda 表达式语法格式：
 * 1、无参数、无返回值：() -> System.out.println("Lambda!")
 * 2、有一个参数、无返回值：(x) -> System.out.println(x)
 * 3、有一个参数（参数小括号可以不写）、无返回值：x -> System.out.println(x)
 *
 * 4、有2个以上参数、有返回值，且 Lambda 体中有多条语句：
 *       Comparator<Integer> comparator = (x, y) -> {
 *           System.out.println(" Lambda 表达式体...");
 *           return Integer.compare(x, y);
 *       };
 *
 * 5、若 Lambda 体中只有一条语句，大括号和 return 关键字都可以省略:
 *      Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 *
 * 6、Lambda 表达式参数列表中的数据类型可以省略不写，因为 JVM 编译器可以通过上下文推断出数据类型，即"类型推断"
 *
 *
 * 总结：箭头符号，左侧遇一括号省；左侧推断类型省
 *  Lambda 表达式需要函数式接口支持
 *      函数式接口：接口中只有一个抽象方法时，称为函数式接口
 *      可以使用 @FunctionalInterface 注解，检查是否是函数式接口
 */
public class LambdaTest1 {

    // Lambda 表达式：无参数、无返回值
    @Test
    public void test1(){
        int num = 0; // jdk1.7以前必须显示声明为 final，jdk 1.8 后，不用显示声明，会自动加上 final

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable 匿名内部类！" + num);// num 值不可变
            }
        };
        //new Thread(r).start();
        r.run();

        System.out.println("----------");

        Runnable r1 = () -> System.out.println("runnable Lambda 表达式！");
        //new Thread(r1).start();
        r1.run();
    }

    // Lambda 表达式：一个参数、无返回值
    @Test
    public void test2(){
        //Consumer<String> con = (x) -> System.out.println(x);
        Consumer<String> con = x -> System.out.println(x);
        con.accept("Yangyy!!!");
    }

    // Lambda 表达式：有2个以上参数、有返回值，且 Lambda 体中有多条语句
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println(" Lambda 表达式体...");
            return Integer.compare(x, y);
        };

        Integer x = 10, y = 18;
        System.out.println(comparator.compare(x, y));// 结果为负数时表示 x < y
    }

    // 若 Lambda 体中只有一条语句，大括号和 return 关键字都可以省略
    @Test
    public void test4(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        Integer x = 10, y = 8;
        System.out.println(comparator.compare(x, y));// 结果为正数时表示 x > y
    }

    // Lambda 表达式参数列表中的数据类型可以省略不写，因为 JVM 编译器可以通过上下文推断出数据类型
    @Test
    public void test5(){
        Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);

        Integer x = 10, y = 10;
        System.out.println(comparator.compare(x, y));// 结果为0时表示 x = y
    }

    // 其他 数据类型推断测试
    @Test
    public void test6(){
        String[] strs = {"aa", "nn", "cc"};//根据前面的数组定义推断出来
        /*
        // 如果拆分成两行写，则编译无法通过
        String[] strs1;
        strs1 = {"aa", "nn", "cc"};*/

        List<Integer> list = new ArrayList<>();//根据 list 定义推断数据类型

        //此处入参 map 的数据类型可以省略，JVM 根据目标方法 show() 的参数类型推断（注意 jdk1.7 中该写法会编译不通过）
        show(new HashMap<>());
    }

    public void show(Map<Integer, String> map){}


    // 需求：对数值进行运算
    @Test
    public void test7(){
        Integer num = 100;
        // Lambda 表达式
        System.out.println(" 平方运算： " + operator(num, (x) -> x * x));

        // 匿名内部类方式
        Integer result = operator(num, new MyFunction<Integer>() {
            @Override
            public Integer getValue(Integer num) {
                return num ++;
            }
        });
        System.out.println("加1 运算 " + result);
    }

    public Integer operator(Integer num, MyFunction<Integer> mf){
        return mf.getValue(num);
    }
}
