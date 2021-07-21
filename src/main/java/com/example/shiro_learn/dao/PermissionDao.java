package com.example.shiro_learn.dao;

import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface PermissionDao {
    Set<String> findPermissionsByUserId(int id);
    Set<String> findPermissionsByUsername(String username);
    String findPermissionByName(String name);
}
