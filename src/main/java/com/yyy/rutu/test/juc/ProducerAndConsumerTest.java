package com.yyy.rutu.test.juc;

/**
 * 生产者-消费者案例：
 * 生产者：不断生产商品
 * 消费者：不断消费商品
 *
 */
/*
public class ProducerAndConsumerTest {
    public static void main(String[] args){
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(producer, "生产者 A").start();
        new Thread(consumer, "消费者 B").start();

        new Thread(producer, "生产者 C").start();
        new Thread(consumer, "消费者 D").start();
    }
}

// 定义店员类：有进货和卖货能力
class Clerk{
    // 初始化商品数量为0
    private int product = 0;

    // 进货：商品数每次加1
    public synchronized void get(){
        // 为了避免虚假唤醒问题，wait()应该总是用在循环中
        while (product >= 1){
            System.out.println("商品已满，不再进货！");
            try {
                this.wait();//满货，当前线程等待
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "：" + ++product);
        this.notifyAll();//已进货，可以卖货了
    }

    // 卖货：商品数每次减1
    public synchronized void sale(){
        // 为了避免虚假唤醒问题，wait()应该总是用在循环中
        while (product <= 0){
            System.out.println("缺货中，请进货！");
            try {
                this.wait();//缺货，当前线程等待
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "：" + --product);
        this.notifyAll();//已卖货，可以进货了
    }
}

// 定义生产者：可能有多个，所以实现 Runnable接口
class Producer implements Runnable{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        // 不断生产商品：假设最多生产20个（店员进货）
        for(int i = 0; i < 20; i++){
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){}
            clerk.get();
        }
    }
}

// 定义消费者：可能有多个，所以实现 Runnable接口
class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        // 不断生产商品：假设最多消费20个（店员卖货）
        for(int i = 0; i < 20; i++){
            clerk.sale();
        }
    }
}
*/