package com.yyy.rutu.sxfy;

import com.yyy.rutu.sxfy.entity.FUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SxfyApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作key-value都是字符串

    @Autowired
    RedisTemplate redisTemplate;//操作key-value都是对象

    @Autowired
    RedisTemplate<Object, FUser> jacksonRedisTemplate;


    @Test
    public void testRedis01(){
        stringRedisTemplate.opsForValue().append("codeStr01", "strStr");//操作字符串
        stringRedisTemplate.opsForList().leftPush("codeList01", "listList");//操作List
        stringRedisTemplate.opsForSet().add("codeSet01", "set1", "set2", "set3");//操作Set

        String s = "youyouoy";
        stringRedisTemplate.opsForHash().put("codeHash01", s, s);//操作Hash（散列)

        /* //报错java.lang.Integer cannot be cast to org.springframework.data.redis.core.ZSetOperations$TypedTuple
        Set set = new HashSet();
        set.add(3);
        set.add(2);
        set.add(1);
        stringRedisTemplate.opsForZSet().add("codeZSet01", set);//操作ZSet（有序集合）*/

    }

    @Test
    public void testRedis02(){
        redisTemplate.opsForValue().append("codeStr01", "000");//操作字符串；给已有的key对应的value追加字符串

        //操作对象
        FUser fUser = new FUser(1, "admin1", "admin1", "yyy1");
        //使用Redis保存对象，默认使用jdk序列化机制，将序列化后的数据保存到Redis中（序列化后的内容无法直观看到）

        redisTemplate.opsForValue().set("user-01", fUser);

    }

    //redisTemplate 操作k-v都是对象
    @Test
    public void testRedis03(){

        //操作对象
        FUser fUser = new FUser(2, "admin2", "admin2", "yyy2");
        //使用Redis保存对象，默认使用jdk序列化机制，将序列化后的数据保存到Redis中（序列化后的内容无法直观看到）

        //1）使用JSON序列化，转化程JSON格式，存入Redis
        //2) 修改序列化器
        jacksonRedisTemplate.opsForValue().set("user-02", fUser);

    }


    @Test
    public void testRedis04(){
        FUser fUser1 = jacksonRedisTemplate.opsForValue().get("user-01");
        System.out.println("fUser1；" + fUser1);

        FUser fUser2 = jacksonRedisTemplate.opsForValue().get("user-02");
        System.out.println("fUser2；" + fUser2);

    }


    @Test
    public void contextLoads() {
        BigDecimal bigDecimal = new BigDecimal(10.0d);
        BigDecimal bigDecimal1 = new BigDecimal(1.1);
        System.out.println(bigDecimal.add(bigDecimal1));

        WebMvcAutoConfiguration configuration;//SpringBoot Mvc自动配置类
        ThymeleafProperties thymeleafProperties;//Thymeleaf模版引擎配置类

    }

}
