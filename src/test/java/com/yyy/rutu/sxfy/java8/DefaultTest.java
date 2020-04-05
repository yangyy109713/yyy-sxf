package com.yyy.rutu.sxfy.java8;

import com.yyy.rutu.test.java8.SubClass;
import org.junit.Test;

public class DefaultTest {

    @Test
    public void test1(){
        SubClass c = new SubClass();
        System.out.println(c.def());

        c.show();
    }

}
