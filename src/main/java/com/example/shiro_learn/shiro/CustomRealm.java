package com.example.shiro_learn.shiro;

import com.example.shiro_learn.entity.User;
import com.example.shiro_learn.service.PermissionService;
import com.example.shiro_learn.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
@Component
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=(String) principalCollection.getPrimaryPrincipal();
        if(null!=username) {
            Set<String> permissions=permissionService.findPermissionsByUsername(username);
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            info.setStringPermissions(permissions);
            return info;
        }else{
            throw new AuthorizationException("授权异常");
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        String username=token.getUsername();
        User user=userService.findUserByUsername(username);
        if(null==user){
            throw new AccountException("账号/密码不正确");
        }else if (Arrays.equals(user.getPassword().toCharArray(), token.getPassword())){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        }else{
//            System.out.println(Arrays.toString(token.getPassword()));
            throw new IncorrectCredentialsException("账号/密码不正确");
        }
    }
}
