package com.yyy.rutu.sxfy.controller;

import com.alibaba.fastjson.JSONObject;
import com.yyy.rutu.sxfy.elastic.FUserLogRepository;
import com.yyy.rutu.sxfy.elastic.OperationLogRepository;
import com.yyy.rutu.sxfy.entity.FDept;
import com.yyy.rutu.sxfy.entity.FUser;
import com.yyy.rutu.sxfy.entity.LogEntity;
import com.yyy.rutu.sxfy.service.FDeptService;
import com.yyy.rutu.sxfy.service.FUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Controller
public class FUserController {

    static Logger logger = LoggerFactory.getLogger(FUserController.class);

    @Autowired
    FUserService fUserService;

    @Autowired
    FDeptService fDeptService;

    @Autowired
    private FUserLogRepository fUserLogRepository;

    @Autowired
    private OperationLogRepository logRepository;


    /*
    //指定到模版引擎使用的首页
    @RequestMapping("/")
    public String index(){
        return "login";
    }
    */


    //用户列表
    @GetMapping("/users")
    public String userList(Model model){
        Collection<FUser> users =  fUserService.getUserList();
        for (FUser user : users){
            FDept dept = fDeptService.getDept(user.getDeptId());
            user.setDept(dept);
        }

        //将用户信息，放在请求域中
        model.addAttribute("users", users);
        users.stream().forEach(fUser -> {
            fUserLogRepository.save(fUser);//将所有用户信息写入ES中
        });

        LogEntity logEntity = new LogEntity();
        logEntity.setOperation("search user list");
        logEntity.setId(2);
        logEntity.setTime(new Date());
        logRepository.save(logEntity);//

        // thymeleaf默认会拼串
        // classpath:/templates/xxxx.html
        return "user/list";
    }


    //添加用户--点击"添加用户"，来到添加页面
    @GetMapping("/user")
    public String toAddPage(Model model){
        //查询出所有部门信息，在页面上显示
        Collection<FDept> depts = fDeptService.getDepts();
        model.addAttribute("depts", depts);
        return "user/add";
    }

    //添加用户--提交
    /**
     * Spring MVC自动将请求参数和入参对象的属性一一对应
     * 要求请求参数的名字和javaBean入参对象里属性名相同
     * @param user 待添加的对象
     * @return 重定向到用户列表页
     */
    @PostMapping("user")
    public String addUser(FUser user){
        //System.out.println("add user："+ user.toString());
        Integer i = fUserService.addUser(user);
        if(i != null){
            logger.info("添加用户："+ user.toString() + " 成功");
            fUserLogRepository.save(user);//新增用户成功，将用户加入ES中

            LogEntity logEntity = new LogEntity();
            logEntity.setOperation("add user");
            logEntity.setName(user.getUserName());
            logEntity.setId(3);
            logEntity.setTime(new Date());
            logRepository.save(logEntity);//

        }
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/users";
    }

    //进入编辑页面，查询当前员工，在页面回显
    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        FUser fUser = fUserService.getUser(id);
        FDept fDept = fDeptService.getDept(fUser.getDeptId());
        fUser.setDept(fDept);

        model.addAttribute("user",  fUser);//用于编辑页面，回显员工信息

        Collection<FDept> depts = fDeptService.getDepts();
        model.addAttribute("depts",  depts);//用于编辑页面，回显所有部门信息

        return "user/add";//和添加页面公用一个页面
    }

    @PutMapping("/user")
    public String editUser(FUser user){
        System.out.println("update user："+ user.toString());
        Integer i = fUserService.updateUser(user);
        if(i != null){
            logger.info("编辑用户："+ user.toString() + " 成功");
            fUserLogRepository.save(user);//编辑用户成功，更新ES中信息

            LogEntity logEntity = new LogEntity();
            logEntity.setOperation("edit user");
            logEntity.setName(user.getUserName());
            logEntity.setId(4);
            logEntity.setTime(new Date());
            logRepository.save(logEntity);//

        }
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}")
    public String delUser(@PathVariable("id") Integer id){
        FUser user = fUserService.getUser(id);
        Integer i = fUserService.deleteUser(id);
        if(i != null){
            logger.info("删除用户："+ user.toString()+ " 成功");
            fUserLogRepository.delete(user);//删除用户成功，将ES中信息删除

            LogEntity logEntity = new LogEntity();
            logEntity.setOperation("delete user");
            logEntity.setName(user.getUserName());
            logEntity.setId(5);
            logEntity.setTime(new Date());
            logRepository.save(logEntity);//

        }
        return "redirect:/users";
    }




    @ResponseBody
    @RequestMapping(value = "/user/getUser",
            produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public String findUser(@RequestParam("id") int id){
        FUser user = fUserService.getUser(id);
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
