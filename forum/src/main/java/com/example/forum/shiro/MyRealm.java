package com.example.forum.shiro;

import com.example.forum.dto.UserDTO;
import com.example.forum.entity.User;
import com.example.forum.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获取主要的主体对，将主体对象强制转换为UserDTO类型
        Object principal = principals.getPrimaryPrincipal();
        UserDTO userDTO = new UserDTO(userMapper.getUserByUsername((String) principal));

        String username = userDTO.getUsername();
        int type = userDTO.getType();

        //注入角色与权限
        //SimpleAuthorizationInfo对象用于存储用户的权限和角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //管理员
        if(type == 1){
            info.addRole("admin");
            info.addRole("online");
        }

        //普通用户
        if(type == 0){
            info.addRole("user");
            info.addRole("online");
        }

        //返回填充了权限和角色的info对象
        return info;
    }

    //自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{

        //获取用户身份信息
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[]) authenticationToken.getCredentials());

        //调用数据库获取用户信息并进行验证与返回
        User user = userMapper.getUserByUsername(username);
        if (user != null && (user.getPassword()).equals(password)) {
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    user.getPassword(),
                    authenticationToken.getPrincipal().toString());
            return info;
        }

        //认证失败
        return null;
    }
}
