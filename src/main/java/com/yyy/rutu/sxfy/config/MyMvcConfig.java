package com.yyy.rutu.sxfy.config;

import com.yyy.rutu.sxfy.component.LoginHandlerInterceptor;
import com.yyy.rutu.sxfy.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

//使用WebMvcConfigurerAdapter扩展SpringMVC的功能
@Configuration
//@EnableWebMvc  //全面接管Spring MVC的配置，即不在使用Spring Boot 对Spring MVC的自动配置（访问首页会空白）
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    public EmbeddedWebServerFactoryCustomizerAutoConfiguration customizerAutoConfiguration(){
        return new EmbeddedWebServerFactoryCustomizerAutoConfiguration();
    }

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
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry;
                //静态资源：*.css , *.js
                //SpringBoot已经做好了静态资源映射
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/webjars/**", "/asserts/**", "/index.html", "/", "/user/login");
            }

        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
