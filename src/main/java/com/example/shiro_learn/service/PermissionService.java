package com.example.shiro_learn.service;

import com.example.shiro_learn.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    public Set<String> findPermissionsByUsername(String username){
        return permissionDao.findPermissionsByUsername(username);
    }
    public Set<String> findPermissionsByUserId(int id){
        return permissionDao.findPermissionsByUserId(id);
    }
    public String findPermissionByName(String name){
        return permissionDao.findPermissionByName(name);
    }
}
