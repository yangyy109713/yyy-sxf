package com.yyy.rutu.sxfy.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeTest {

    // LocalDate、LocalTime、LocalDateTime
    // 使用方法法相似
    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt1 = LocalDateTime.of(2020, 4, 5, 11, 42, 30);
        System.out.println(ldt1);

        LocalDateTime ldt2 = ldt.plusYears(2);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.minusMonths(2);
        System.out.println(ldt3);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

    // Instant：时间戳（以 Unix 元年：1970 年 1 月 1 日 到 某一时间 之间的毫秒值）
    // 机器读取的时间格式
    @Test
    public void test2(){
        Instant ins1 = Instant.now();
        System.out.println(ins1);// 默认获取 UTC 时区

        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(ins1.toEpochMilli());// 获取毫秒值

        Instant ins2 = Instant.ofEpochSecond(60);// 元年 + 60 秒
        System.out.println(ins2);// 1970-01-01T00:01:00Z
    }

    // Duration：计算两个时间之间的间隔
    @Test
    public void test3(){
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(1061);
        }catch (InterruptedException e){}
        Instant ins2 = Instant.now();
        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.getSeconds());
        System.out.println(duration.toMillis());
        System.out.println(duration.getNano());// 获取舍弃"整秒"的毫米对应的纳秒值：1毫秒(ms)=1000000纳秒(ns)

        System.out.println("----------------------");

        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){}
        LocalTime lt2 = LocalTime.now();
        Duration duration1 = Duration.between(lt1, lt2);
        System.out.println(duration1.getSeconds());
        System.out.println(duration1.toMillis());
        System.out.println(duration1.getNano());
    }

    // Period：计算两个日期之间的间隔
    @Test
    public void test4(){
        LocalDate ld1 = LocalDate.of(2013, 2, 5);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1,ld2);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    // TemporalAdjuster：时间校正器
    @Test
    public void test5(){
        LocalDateTime ld1 = LocalDateTime.now();
        System.out.println(ld1);

        // 制定年月日 时分秒
        LocalDateTime ld2 = ld1.withDayOfYear(201);
        //LocalDateTime ld3 = ld1.withDayOfYear(50).withDayOfMonth(30);
        LocalDateTime ld3 = ld1.withDayOfYear(50).withDayOfMonth(29);
        System.out.println(ld2);
        System.out.println(ld3);

        LocalDateTime ld4 = ld1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ld4);

        // 自定义
        LocalDateTime ld5 = ld1.with((tl) -> {
            return null;// 此处写自定义的逻辑
        });
    }

    // DateTimeFormatter
    @Test
    public void test6(){
        //DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;// 默认的转换格式
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;// 默认的转换格式
        String strDate = dtf.format(LocalDateTime.now());
        System.out.println(strDate);// 2020-04-05

        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate1 = dtf1.format(LocalDateTime.now());
        System.out.println(strDate1);

        LocalDateTime ldt1 = (LocalDateTime.now()).parse(strDate1, dtf1);
        System.out.println(ldt1);
    }

    // ZonedDate、ZonedTime、ZonedDateTime

}
