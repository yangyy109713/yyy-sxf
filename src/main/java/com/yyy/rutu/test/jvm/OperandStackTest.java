package com.yyy.rutu.test.jvm;

public class OperandStackTest {

    public void testAddOperation(){
        byte i = 15;
        int j = 8;
        int k = i + j;
    }

    public int getSum(){
        byte i = 15;
        int j = 8;
        int k = i + j;
        return k;
    }

    public void testSum(){
        int s = getSum();
        int m = 11;
    }

    public void addTest(){
        // i++ 与 ++i 的区别

        // 第1类问题：
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;

        // 第2类问题：
        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        // 第3类问题：
        int i7 = 10;
        i7 = i7++;

        int i8 = 10;
        i8 = ++i8;

        // 第4类问题：
        int i9 = 10;
        int i10 = i9++ + ++i9;

    }
}
