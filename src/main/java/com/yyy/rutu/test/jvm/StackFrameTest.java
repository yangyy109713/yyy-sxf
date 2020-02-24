package com.yyy.rutu.test.jvm;

/**
 * 方法的结束方式分为两种：
 * 1、正常结束，以 return 指令返回结果的方式结束
 * 2、方法执行过程中出现未捕获的异常，以抛出异常的方式结束
 */
public class StackFrameTest {

    public static void main(String[] args){
        try {
            new StackFrameTest().method1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 为了在反编译后能看到对应方法，将类型改为 public
    public void method1(){
        System.out.println("method1 开始执行...");
        method2();
        System.out.println("method1 执行结束...");
        System.out.println(10 / 0);// 模拟抛出异常
    }

    public int method2(){
        System.out.println("method2 开始执行...");
        int i = 10;
        int m = (int) method3();
        System.out.println("method2 即将结束...");
        return i + m;
    }

    public double method3(){
        System.out.println("method3 开始执行...");
        double j = 20.0;
        System.out.println("method3 即将结束...");
        return j;
    }
}
