package com.example.shiro_learn.service;

import com.example.shiro_learn.dao.UserDao;
import com.example.shiro_learn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User findUserByUsername(String username){
        return userDao.findUserByUsername(username);
    }
    public User findUserById(int id){
        return userDao.findUserById(id);
    }
}
