package com.yyy.rutu.sxfy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SxfyApplicationTests {

    @Test
    public void contextLoads() {
        BigDecimal bigDecimal = new BigDecimal(10.0d);
        BigDecimal bigDecimal1 = new BigDecimal(1.1);
        System.out.println(bigDecimal.add(bigDecimal1));
    }

}
