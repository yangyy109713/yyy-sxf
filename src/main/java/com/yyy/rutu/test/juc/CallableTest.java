package com.yyy.rutu.test.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建 执行线程的方式三：实现 Callable 接口
 * Callable 接口，方法有返回值且可以抛出异常
 * 执行 Callable 接口方式的线程，需要 FutureTask 实现类的支持，用于接受运算结果
 * FutureTask（java.util.concurrent.FutureTask） 是 Future 接口的实现类
 * FutureTask：也可用于闭锁，提高执行效率
 */
public class CallableTest {
    public static void main(String[] args){
        CallableDemo demo = new CallableDemo();
        //1.执行 Callable 接口方式的线程，需要 FutureTask 实现类的支持，用于接受运算结果
        FutureTask<Integer> task = new FutureTask<>(demo);
        new Thread(task).start();
        //2.接收运算结果
        try {
           int sum = task.get();
           System.out.println("总和：" + sum);
        }catch (InterruptedException | ExecutionException e1){}
    }
}

class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++){
            System.out.println(i);
            sum += i;
        }
        return sum;
    }
}
