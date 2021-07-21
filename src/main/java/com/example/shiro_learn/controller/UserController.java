package com.example.shiro_learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shiro_learn.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
//    @ExceptionHandler(UnauthenticatedException.class)
//    public JSONObject handleException(UnauthenticatedException e){
//        JSONObject jsonObject=new JSONObject();
//        e.printStackTrace();
//        jsonObject.put("error",e);
//        return jsonObject;
//    }
    @PostMapping()
//    @RequiresPermissions("user:select")
    @RequiresAuthentication
    public JSONObject getNowUser(){
        JSONObject jsonObject=new JSONObject();
        Subject subject=SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            User user=new User();
            user.setUsername((String) subject.getPrincipal());
            jsonObject.put("user",user);
            jsonObject.put("result","success");
        }else{
            System.out.println("error");
            jsonObject.put("result","failed");
        }
        return jsonObject;
    }
    @PostMapping("/login")
    public JSONObject login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        Subject subject = null;
        if (null != user) {
            subject = SecurityUtils.getSubject();
            try {
                subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
                jsonObject.put("user",user);
                jsonObject.put("result","success");
            }catch ( UnknownAccountException | LockedAccountException | IncorrectCredentialsException uae ) {
//                uae.printStackTrace();
                jsonObject.put("error",uae);
                jsonObject.put("result","failed");
            }
        }else{
            jsonObject.put("error","error");
            jsonObject.put("result","failed");
        }
//        assert subject != null;
//        System.out.println(subject.isAuthenticated());
//        System.out.println(subject.isPermitted("user:add"));
        return jsonObject;
    }
    @PostMapping("/add")
//    @RequiresAuthentication
    @RequiresPermissions("user:add")
    public JSONObject addUser(@RequestBody User user){
        System.out.println(user.getUsername());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result","success");
        return jsonObject;
    }
}
