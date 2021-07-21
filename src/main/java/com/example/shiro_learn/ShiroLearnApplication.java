package com.example.shiro_learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.shiro_learn.dao")
@SpringBootApplication
public class ShiroLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroLearnApplication.class, args);
    }
}