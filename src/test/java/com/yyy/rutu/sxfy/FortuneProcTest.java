package com.yyy.rutu.sxfy;

import org.junit.Test;

public class FortuneProcTest {
    @Test
    public void testSlaveProc(){
        // 123325 = rider_id
        // 收单分库
        int modValue = 123325 % 256 / 16;
        System.out.println(modValue + 1);

        // 收单分表
        int modValue1 = 123325 % 16;
        System.out.println(modValue1 + 1);
    }

    @Test
    public void testSchedule(){
        // 调度分片数
        // SCHEDULE_SHARDX_NUM = 8;
        // 线上策略：每台机器 2 个线程执行 合单结算任务（QA1 仅有一台机器一个线程）
        int modValue = 123443 % 8;
        System.out.println(modValue);
    }

    @Test
    public void testRider(){
        // 骑手分库分表
        // 骑手ID 除 8 取余 + 1
        int value = (385545 % 8) + 1;
        System.out.println(value);
    }

    @Test
    public void testRiderAccount(){
        // 骑手账户分库分表（保证分库中骑手信息唯一性）
        // 如 123443 计算 x=5，只能在 cobarb_shard5.rider 中有一条记录，其他分库若存在账户不可用
        //int x = 5041471 % 1024 / 128 + 1 ;
        int x = 385545 % 1024 / 128 + 1 ;
        System.out.println(x);
    }
}
