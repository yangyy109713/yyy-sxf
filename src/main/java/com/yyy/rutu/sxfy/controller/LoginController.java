package com.yyy.rutu.sxfy.controller;

import com.yyy.rutu.sxfy.entity.FUser;
import com.yyy.rutu.sxfy.service.FUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    static Logger logger = LoggerFactory.getLogger(FUserController.class);

    @Autowired(required = false)
    FUserService fUserService;

    // @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    // @DeleteMapping
    // @PutMapping
    // @GetMapping
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        FUser fUser = fUserService.getUserByNameAndPass(username, password);
        if(fUser != null && fUser.getId() != null){
            logger.info("For test: " + fUser.toString());
            //登陆成功，防止表单重复提交，重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            //登陆失败
            map.put("msg","用户名密码错误");
            return  "login";
        }

    }
}
