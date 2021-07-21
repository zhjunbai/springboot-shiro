package com.example.shiro_learn.global;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public JSONObject handleException(Exception e){
        System.out.println("handle error");
        e.printStackTrace();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("error",e);
        jsonObject.put("result","failed");
        return jsonObject;
    }
}