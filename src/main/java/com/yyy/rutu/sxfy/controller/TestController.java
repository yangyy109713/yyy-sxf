package com.yyy.rutu.sxfy.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public String test1(@RequestParam("num") int num){
        logger.info("Request is beginning ...");
        return "Request is successful :testNumber is " +num;
    }

    /**
     * 通过@ResponseBody和@RequestMapping(value = "/request/data", method = RequestMethod.POST,
     * produces = "application/json;charset=UTF-8")中的
     * produces = "application/json;charset=UTF-8"来设定返回的数据类型是json，utf-8编码
     * @param jsonObject 入参
     * @return 字符串
     */
    @RequestMapping(value = "/body/data", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String writeByBody(@RequestBody JSONObject jsonObject){
        //打印获取到的jsonObject
        System.out.println(jsonObject.toJSONString());

        // 将获取的json数据封装,然后再返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "@ResponseBody");
        result.put("data", jsonObject);

        return result.toJSONString();
    }

    /**
     * 通过HttpServletResponse来返回
     * 1.HttpServletResponse 获取到输出流
     * 2.写出数据到客户端
     */
    @RequestMapping(value = "/resp/data", method = RequestMethod.POST)
    public void witeByResp(@RequestBody JSONObject jsonObject, HttpServletResponse response){
        // 将获取的json数据封装,然后再返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "HttpServletResponse");
        result.put("data", jsonObject);

        //写数据到客户端
        PrintWriter printWriter = null;
        try {
            //设定json格式
            response.setContentType("application/json;charset=UTF-8");
            printWriter = response.getWriter();

            //写数据
            printWriter.write(result.toJSONString());
            printWriter.flush();

        }catch (IOException i){
            i.printStackTrace();
        }finally {//关闭写数据流
            if(printWriter != null){
                printWriter.close();
            }
        }
    }

}
