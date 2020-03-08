package com.gk.secondhand.shiro;

import com.gk.secondhand.entity.User;
import com.gk.secondhand.service.impl.UserServiceImpl;
import com.gk.secondhand.util.MD5;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    @Autowired
    UserServiceImpl userService;

    /**
     * 执行一些认证的逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;

        User cur_user = userService.getUserByPhone(token.getUsername());
       /* if (!token.getUsername().equals(cur_user.getPhone())){*/
        if(cur_user==null){
            //用户不存在
            System.out.println("用户不存在");
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo("", cur_user.getPassword(),"");
    }
}
