package com.yyy.rutu.sxfy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter扩展SpringMVC的功能
@Configuration
//@EnableWebMvc  //全面接管Spring MVC的配置，即不在使用Spring Boot 对Spring MVC的自动配置（访问首页会空白）
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //向浏览器发送 /con 请求，会来到success页面
        registry.addViewController("/con").setViewName("success");
    }

    // 将组件注册到容器中
    // 所有的WebMvcConfigurer组件都会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //向浏览器发送默认请求，会来到 login 页
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
            }
        };
        return adapter;
    }
}
