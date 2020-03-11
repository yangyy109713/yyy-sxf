package com.yyy.rutu.test.juc;

/**
 * 模拟 CAS 算法
 */
public class CompareAndSwapTest {
    public static void main(String[] args){
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++){
            // 创建10个线程，模拟 CAS 算法
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.getValue();
                    boolean b = cas.compareAndSwapSet(expectedValue, (int)(Math.random()* 101));
                    System.out.println("模拟 CAS 算法结果："+ b);// 打印结果不全是 false
                }
            }).start();
        }
    }
}

class CompareAndSwap{
    private int value;

    // 获取内存值
    public synchronized int getValue() {
        return value;
    }

    // 比较
    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if(oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }

    // 设置值
    public synchronized boolean compareAndSwapSet(int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}