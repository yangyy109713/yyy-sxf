package com.yyy.rutu.sxfy;

import com.yyy.rutu.sxfy.entity.LogEntity;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonFormatTest {

    @Test
    public void test() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        Date time = sdf.parse(date);
        LogEntity logEntity = new LogEntity("test", "test", time);
        System.out.println(logEntity.getTime());
    }
}
