package com.yyy.rutu.test.jvm;

public class ClassInitTest {
    private static int num = 1;

    static {
        num = 2;
        number = 20;//这种赋值方式是合法的

        System.out.println(num);// 打印不会报错
        //System.out.println(number);// 打印会报错：Illegal forward reference（非法的前向引用）

    }
    // 因为在类的加载过程 linking 的 prepare 阶段，已经将 number 赋值为0
    // 在类的加载过程 initialization 过程中进行覆盖：先赋值 20，再赋值10
    private static int number = 10;

    public static void main(String[] args){
        System.out.println(ClassInitTest.num);// 打印结果2
        System.out.println(ClassInitTest.number);// 打印结果10
    }
}
