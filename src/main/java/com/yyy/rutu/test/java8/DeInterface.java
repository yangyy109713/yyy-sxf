package com.yyy.rutu.test.java8;

interface DeInterface {

    // 接口中具有实现的方法，称为默认方法
    default String def(){
        return "哈哈哈哈哈";
    }

    // 接口中的静态方法
    static void show(){
        System.out.println("呀呀呀呀呀呀");
    }
}
