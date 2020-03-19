package com.yyy.rutu.sxfy.java8;

// 使用下面注解，表示该接口是 函数式接口
@FunctionalInterface
public interface MyFunction<T> {
    Integer getValue(Integer num);
}
