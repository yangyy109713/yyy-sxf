package com.yyy.rutu.test.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行
 * 例子：开启10个线程，计算10个线程全部执行完，需要多少时间
 */
public class CountDownLatchTest {
    public static void main(String[] args){
        final CountDownLatch latch = new CountDownLatch(10);
        /*
         * 参数的意义：表示子线程的个数，以递减方式执行子线程；减至0时执行主线程
         * 同时在子线程中，执行完后需要将传入参数做减1操作:latch.countDown();
         * 并且要保证latch.countDown();一定会执行，所以需要放入try-finally结构中
         * 可能会由线程安全问题，所以需要加锁
         */
        long begin = System.currentTimeMillis();
        LatchDemo latchDemo = new LatchDemo(latch);
        for (int i = 0; i < 10; i++){
            new Thread(latchDemo).start();
        }

        // 若子线程未执行完，主线程要一直等待
        try {
            latch.await();
        }catch (InterruptedException e){}

        long end = System.currentTimeMillis();
        System.out.println("总共耗时：" + (end - begin) + "毫秒");
    }
}

class LatchDemo implements Runnable{

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        // 可能会由线程安全问题，所以需要加锁
        synchronized(this){
            try {
                for (int i = 0; i < 1000; i++){
                    if(i % 2 == 0){
                        System.out.println(i + "是偶数！");
                    }
                }
            }finally {
                // 要保证latch.countDown();一定会执行
                latch.countDown();
            }
        }
    }
}