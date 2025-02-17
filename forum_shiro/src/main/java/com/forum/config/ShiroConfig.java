package com.forum.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.forum.shiro.MyRealm;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class ShiroConfig {

    //定义一个Spring Bean，该Bean是Shiro框架的DefaultWebSecurityManager，负责Web应用的安全性管理，包括认证和授权。
    //这个Bean被配置为使用一个自定义的Realm（MyRealm），并且在其初始化时记录一条日志信息。
    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(realm);
        log.info("securityManager -----------> 初始化了");
        return securityManager;
    }

    //配置Shiro的安全过滤器，定义哪些URL可以匿名访问，哪些URL需要特定的角色才能访问，以及当用户未认证或没有权限时应该重定向到哪个URL
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置当用户未认证时重定向的URL
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        //设置当用户没有权限访问某个资源时重定向的URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");


        Map<String, String> hashMap = new LinkedHashMap<>();

        //swagger2
        hashMap.put("/swagger-ui.html", "anon");
        hashMap.put("/swagger/**", "anon");
        hashMap.put("/swagger-resources/**", "anon");
        hashMap.put("/v2/**", "anon");
        hashMap.put("/webjars/**", "anon");
        hashMap.put("/configuration/**", "anon");

        //account
        hashMap.put("/account/checkPassword", "anon");
        hashMap.put("/account/changPassword", "anon");
        hashMap.put("/account/changMessage", "anon");
        hashMap.put("/account/checkUsername", "anon");
        hashMap.put("/login", "anon");
        hashMap.put("/account/signOut", "anon");
        hashMap.put("/signup", "anon");
        hashMap.put("/signup/admin", "anon");
        hashMap.put("/account/user/all", "roles[user]");
        hashMap.put("/article","anon");
        hashMap.put("/userInfo","anon");

        //adminOrder
        hashMap.put("/admin_order/deal", "roles[admin]");
        hashMap.put("/admin_order/detail", "roles[admin]");
        hashMap.put("/admin_order/message", "roles[admin]");
        hashMap.put("/admin_order/search", "roles[admin]");
        hashMap.put("/admin_order/list", "roles[admin]");

        //car
        hashMap.put("/car/list", "roles[admin]");
        hashMap.put("/car/add", "roles[admin]");
        hashMap.put("/car/delete", "roles[admin]");
        hashMap.put("/car/free", "roles[admin]");

        //driver
        hashMap.put("/driver/list", "roles[admin]");
        hashMap.put("/driver/add", "roles[admin]");
        hashMap.put("/driver/delete", "roles[admin]");
        hashMap.put("/driver/free", "roles[admin]");

        //userOrder
        hashMap.put("/user_order/submit", "roles[user]");
        hashMap.put("/user_order/detail", "roles[user]");
        hashMap.put("/user_order/message", "roles[user]");
        hashMap.put("/user_order/search", "roles[user]");
        hashMap.put("/user_order/list", "roles[user]");
        hashMap.put("/user_order/receive", "roles[user]");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 开启aop注解支持
     * 即在controller中使用 @RequiresPermissions("user/userList")
     */
    //支持基于注解的安全授权
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        //设置安全管理器
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    //定义并配置一个名为defaultAdvisorAutoProxyCreator的bean，它是Spring Security框架的一部分，用于支持基于注解的安全授权
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }




    // shiro-redis
    //====== session共享 ========
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return 1
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1:6379");
        redisManager.setDatabase(0);
        return redisManager;
    }

    /**
     * DAO大家都用过，数据访问对象，用于会话的CRUD，比如我们想把Session保存到数据库，那么可以实现自己的SessionDAO，
     * 通过如JDBC写到数据库；比如想把Session放到Memcached中，可以实现自己的Memcached SessionDAO；
     * 另外SessionDAO中可以使用Cache进行缓存，以提高性能；
     */
    @Bean
    RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**
     * 缓存控制器，来管理如用户、角色、权限等的缓存的；
     * 因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能
     */
    @Bean
    RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存(此处的id需要对应user实体中的id字段,用于唯一标识)
        redisCacheManager.setPrincipalIdFieldName("id");
        //用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }

}
