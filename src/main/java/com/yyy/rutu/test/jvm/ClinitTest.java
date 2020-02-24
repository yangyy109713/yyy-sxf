package com.yyy.rutu.test.jvm;

public class ClinitTest {

    private int a = 1;

    private static int c = 3;

    public static void main(String[] args){
        int b = 2;
    }

    /**
     * 当前类中未声明静态变量，也没有静态代码块
     * 问题：此时字节码结构中会存在 类构造器方法 <clinit>() 吗？
     * 结论：不存在
     * 原因：<clinit>() 是 javac 编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来
     *      如果没有变量赋值动作或 静态代码块，就不会有 <clinit>()
     */


    public ClinitTest(){
        a = 10;
        int d = 20;
    }

    /**
     * 任意一个类编译后的字节码文件中都会有 <init> 方法，此方法即为 类的构造函数
     * Ps：任何一个类声明后，内部至少存在一个类的构造器（可以是自己声明的，也可以是默认的）
     */

}
