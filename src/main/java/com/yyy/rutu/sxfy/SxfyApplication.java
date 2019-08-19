package com.yyy.rutu.sxfy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SxfyApplication {

    private static Logger logger = LoggerFactory.getLogger(SxfyApplication.class);

    public static void main(String[] args) {
        logger.info("----- start SxfyApplication -----");
        SpringApplication.run(SxfyApplication.class, args);
        logger.info("----- start SxfyApplication success -----");
    }

}
