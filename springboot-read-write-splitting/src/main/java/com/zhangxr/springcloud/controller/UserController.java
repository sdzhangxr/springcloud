package com.zhangxr.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangxr.springcloud.entities.TUser;
import com.zhangxr.springcloud.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @className UserController
 * @Description TODO
 * @Author sdzha
 * @Date 2021/1/29 15:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/insertUser")
    public String insertUser(@RequestBody TUser user){
        int i = userService.insertUser(user);
        return JSONObject.toJSONString(i);
    }

    @GetMapping("/getUserById")
    public String getUserById(int id){
        TUser user = userService.getUserById(id);
        return JSONObject.toJSONString(user);
    }
}
