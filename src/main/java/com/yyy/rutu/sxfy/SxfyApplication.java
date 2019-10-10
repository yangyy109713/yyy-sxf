package com.yyy.rutu.sxfy;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 开启Elasticsearch
 * 使用注解 @EnableElasticsearchRepositories
 * basePackages指向elasticsearch仓储类所在的包
 */
@EnableElasticsearchRepositories(basePackages = "com.yyy.rutu.sxfy.elastic")
@SpringBootApplication
//指定要扫描的mapper
@MapperScan(value = "mapper")
public class SxfyApplication {

    private static Logger logger = LoggerFactory.getLogger(SxfyApplication.class);

    public static void main(String[] args) {
        logger.info("----- start SxfyApplication -----");
        SpringApplication.run(SxfyApplication.class, args);
        logger.info("----- start SxfyApplication success -----");
    }

}
