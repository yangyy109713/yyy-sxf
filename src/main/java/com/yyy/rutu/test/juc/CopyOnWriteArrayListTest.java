package com.yyy.rutu.test.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList：写入并复制
 * 注意：当添加操作多时，效率低，因为每次添加时都会进行复制，开销非常大。
 * 不建议用在添加操作较多的地方，当并发迭代操作多时，可选择使用用以提高效率。
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args){
        CopyOnWriteArrayListThread copyThread = new CopyOnWriteArrayListThread();
        for (int i = 0 ; i < 10; i++ ) {
            new Thread(copyThread).start();
        }
    }
}

class CopyOnWriteArrayListThread implements Runnable{

    //private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());

            list.add("AA");// 在进行迭代的同时给集合添加元素会出现并发修改异常:java.util.ConcurrentModificationException，因为操作的是统一个数据源
        }
    }
}