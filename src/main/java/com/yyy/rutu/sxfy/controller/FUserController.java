package com.yyy.rutu.sxfy.controller;

import com.alibaba.fastjson.JSONObject;
import com.yyy.rutu.sxfy.entity.FUser;
import com.yyy.rutu.sxfy.service.FUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class FUserController {

    @Autowired
    FUserService fUserService;

    @ResponseBody
    @RequestMapping(value = "/findUser",
            produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public String findUser(@RequestParam("id") int id){
        FUser user = fUserService.findUser(id);

        String jsonStr = JSONObject.toJSONString(user);//转成JSONObject字符串，返回给前端

        return jsonStr;
    }
}
