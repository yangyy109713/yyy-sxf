package com.yyy.rutu.sxfy;

import org.junit.Test;

import java.math.BigDecimal;

public class RecruitRiderDiffOrdersTest {

    @Test
    public void test(){
        BigDecimal C = new BigDecimal(42);
        BigDecimal S = new BigDecimal(30);
        BigDecimal M = new BigDecimal(12);

        BigDecimal Sa = new BigDecimal(20);
        BigDecimal Sb = new BigDecimal(22);

        BigDecimal Sra1 = new BigDecimal(9);//111519
        BigDecimal Sra2 = new BigDecimal(11);///111367


        BigDecimal Srb1 = new BigDecimal(8);
        BigDecimal Srb2 = new BigDecimal(8);
        BigDecimal Srb3 = new BigDecimal(6);//34321


        BigDecimal Ra1 = (M.multiply(Sa.divide(C, BigDecimal.ROUND_DOWN, 0))).multiply(Sra1.divide(Sa, BigDecimal.ROUND_DOWN, 0)).setScale(BigDecimal.ROUND_DOWN, 0);
        BigDecimal Ra2 = (M.multiply(Sa.divide(C, BigDecimal.ROUND_DOWN, 0))).multiply(Sra2.divide(Sa, BigDecimal.ROUND_DOWN, 0)).setScale(BigDecimal.ROUND_DOWN, 0);

        BigDecimal Rb1 = (M.multiply(Sb.divide(C, BigDecimal.ROUND_DOWN, 0))).multiply(Srb1.divide(Sb, BigDecimal.ROUND_DOWN, 0)).setScale(BigDecimal.ROUND_DOWN, 0);
        BigDecimal Rb2 = (M.multiply(Sb.divide(C, BigDecimal.ROUND_DOWN, 0))).multiply(Srb2.divide(Sb, BigDecimal.ROUND_DOWN, 0)).setScale(BigDecimal.ROUND_DOWN, 0);

        System.out.println(Ra1.toString());
        System.out.println(Ra1.toString());
        System.out.println(Rb1.toString());
        System.out.println(Rb2.toString());
    }
}
