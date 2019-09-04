package com.yyy.rutu.sxfy.controller;

import com.yyy.rutu.sxfy.service.FUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class FUserController {

    @Autowired
    FUserService fUserService;

    @ResponseBody
    @RequestMapping(value = "/getUser",
            produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public String getUser(@RequestParam("id") int id){
        return fUserService.findUser(id).toString();
    }
}
