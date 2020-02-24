package com.yyy.rutu.test.jvm;

/**
 * MacBook Pro
 * 默认栈大小 count = 10823
 * 设置栈的大小 -Xss256k
 */
public class StackOverFlowErrorTest {

    private static int count = 1;

    public static void main(String[] args){
        System.out.println(count);//打印结果为栈的默认大小
        count ++;
        main(args);//递归：无限调用main方法，用于演示栈溢出的异常
    }

}
