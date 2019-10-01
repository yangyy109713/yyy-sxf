package com.yyy.rutu.sxfy.controller;

import com.alibaba.fastjson.JSONObject;
import com.yyy.rutu.sxfy.entity.FUser;
import com.yyy.rutu.sxfy.service.FUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Controller
public class FUserController {

    static Logger logger = LoggerFactory.getLogger(FUserController.class);

    @Autowired
    FUserService fUserService;


    /*
    //指定到模版引擎使用的首页
    @RequestMapping("/")
    public String index(){
        return "login";
    }
    */


    @ResponseBody
    @RequestMapping(value = "/findUser",
            produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public String findUser(@RequestParam("id") int id){
        FUser user = fUserService.findUser(id);
        logger.info("根据id查询用户：" + user);
        String jsonStr = JSONObject.toJSONString(user);//转成JSONObject字符串，返回给前端

        return jsonStr;
    }

    @RequestMapping(value = "/success")
    public String success(Map<String, Object> map){
        //会自动找到classpath:/templates/success.html
        map.put("hello", "hello Thymeleaf!");
        map.put("hello1", "<h1>hello1</h1>");
        map.put("users", Arrays.asList("<h2>yyy1</h2>", " <h2>yyy2</h2>", " <h2>yyy3</h2>"));
        return "success";
    }


}
