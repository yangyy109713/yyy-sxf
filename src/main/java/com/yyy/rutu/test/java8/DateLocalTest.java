package com.yyy.rutu.test.java8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DateLocalTest {

    /**
     * java 8 日期时间
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            // 不管做怎样的改变，都会产生一个新的实例，所以是线程安全的
            public LocalDate call() {
                return LocalDate.parse("20200405", dtf);
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future<LocalDate>> results = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            results.add(pool.submit(task));
        }
        for (Future<LocalDate> f : results){
            System.out.println(f.get());
        }

        pool.shutdown();
    }
}
