package com.yyy.rutu.test.java8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OldDateTest {

    /**
     * 执行后会把错：出现线程安全问题
     * Exception in thread "main" java.util.concurrent.ExecutionException: java.lang.NumberFormatException: For input string: "..202020200E"
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                // return sdf.parse("20200405");
                return DateFormatThreadLocal.convert("20200405");// 解决线程安全问题
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            results.add(pool.submit(task));
        }
        for (Future<Date> f : results){
            System.out.println(f.get());
        }

        pool.shutdown();
    }
}
