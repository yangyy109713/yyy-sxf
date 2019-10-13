package com.yyy.rutu.sxfy.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在连接上携带区域信息
 */
public class MyLocaleResolver implements LocaleResolver {
    
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");//request中带过来的参数，如@{/index.html(l=zh_CN)
        Locale locale = Locale.getDefault();//如果没带参数，使用默认的区域信息
        if(!StringUtils.isEmpty(l)){//参数不为空时，根据传参，取国际化配置信息
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
