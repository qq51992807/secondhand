package com.gk.secondhand.shiro;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 创建shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * shiro内置过滤器，权限相关拦截
         * anon：无需认证（登录）可以访问
         * authc：必须认证才可以访问
         * user：如果使用remember可以直接访问
         * perms：该资源必须得到资源权限才可访问
         * role：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap=new LinkedHashMap<String, String>();
        //filterMap.put("/**","anon");
       filterMap.put("/user/login","anon");
        filterMap.put("/user/addUser","anon");
        filterMap.put("/user/register","anon");
        filterMap.put("/user/**","authc");
        filterMap.put("/orders/**","authc");


//        filterMap.put("","anon");
        shiroFilterFactoryBean.setLoginUrl("/goods/homeGoods");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建realm对象
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
