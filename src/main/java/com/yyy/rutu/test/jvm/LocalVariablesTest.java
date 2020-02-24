package com.yyy.rutu.test.jvm;

import java.util.Date;

public class LocalVariablesTest {

    private int count = 0;

    public static void main(String[] args){
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        test.test1();
    }

    // 练习：自己定义一个静态方法，按上述方法分析字节码指令
    public static void testStatic(){
        LocalVariablesTest test = new LocalVariablesTest();
        Date date = new Date();
        int count = 10;
        System.out.println(count);
    }

    // 关于 Slot 的使用的理解
    public LocalVariablesTest(){
        this.count = 1;
    }

    public void test1(){
        Date date = new Date();
        String name1 = "yyy";
        String info = test2(date, name1);
        System.out.println(date + name1);
    }

    public String test2(Date date, String name){
        date = null;
        name = "yangyy";
        double weight = 58.0;// double 类型占据2个 slot
        char gender = '男';
        return  date + name + weight + gender;
    }

    public void test3(){
        this.count ++;
    }

    public void test4(){
        int a = 0;

        {
            int b = 0;
            b = a + 1;
        }

        int c = a + 1;//变量 c 使用之前已经销毁的变量 b 占据的 slot 的位置
    }
}
