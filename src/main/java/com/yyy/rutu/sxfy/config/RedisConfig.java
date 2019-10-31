package com.yyy.rutu.sxfy.config;

import com.yyy.rutu.sxfy.entity.FUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    @Bean //专门用来序列化FUser
    public RedisTemplate<Object, FUser> jacksonRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, FUser> template = new RedisTemplate();
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(FUser.class);
        template.setDefaultSerializer(jsonRedisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
