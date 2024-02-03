package com.example.forum.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.example.forum.shiro.MyRealm;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;

    //定义一个安全管理器Bean，并通过领域（Realm）设置其安全策略
    @Bean(value = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(myRealm);
        log.info("securityManager -----------> 初始化了");
        return securityManager;
    }

    //配置shiro内置过滤器拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //设置不认证可以访问的资源
        definition.addPathDefinition("/signup", "anon");
        definition.addPathDefinition("/login", "anon");
        //设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**", "authc");
        return definition;
    }

    //定义Shiro的过滤器配置，包括安全管理器、登录和未授权访问的URL、以及URL路径和相应的权限
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        /*
        //自定义过滤器
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // 注意这里不要用Bean的方式，否则会报错
        filters.put("myRole", new MyRoleFilter());
        shiroFilterFactoryBean.setFilters(filters);
         */

        //设置Shiro过滤器的安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置登录URL为"/unauth"
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        //设置未授权访问的URL为"/unauth"
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");

        //存储URL路径和相应的权限
        Map<String, String> hashMap = new LinkedHashMap<>();

        //hashMap.put("/**", "authc");
        //test
        hashMap.put("/unauth", "anon");
        hashMap.put("/userTest", "roles[user]");
        hashMap.put("/adminTest", "roles[admin]");
        hashMap.put("/anonTest", "anon");
        hashMap.put("/exception", "anon");

        //swagger2
        hashMap.put("/swagger-ui.html", "anon");
        hashMap.put("/swagger/**", "anon");
        hashMap.put("/swagger-resources/**", "anon");
        hashMap.put("/v2/**", "anon");
        hashMap.put("/webjars/**", "anon");
        hashMap.put("/configuration/**", "anon");

        //account
        hashMap.put("/password", "anon");
        hashMap.put("/verifyCode/password", "anon");
        //hashMap.put("/auth", "roles[user]");
        hashMap.put("/login", "anon");
        hashMap.put("/logout", "anon");
        hashMap.put("/signup", "anon");
        hashMap.put("/signUp/**", "anon");

        //设置所有其他路径的权限为"anon"
        hashMap.put("/**", "anon");

        //将定义的URL路径和权限设置到Shiro过滤器中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);

        //返回配置完成的shiroFilterFactoryBean对象
        return shiroFilterFactoryBean;
    }


    /**
     * 开启aop注解支持
     * 即在controller中使用 @RequiresPermissions("user/userList")
     */
    //提供授权属性的来源，并根据这些属性决定哪些方法或类需要安全访问控制
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        //设置安全管理器
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    //自动代理创建的DefaultAdvisorAutoProxyCreator
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * 处理未授权的异常，返回自定义的错误页面（403）
     * @return
     */
     @Bean
     public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
         SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
         Properties properties = new Properties();
         //未授权处理页
         properties.setProperty("UnauthorizedException", "classpath:403.html");
         resolver.setExceptionMappings(properties);
         return resolver;
     }





    // shiro-redis
    //====== session共享 ========
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    /*
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1:6379");
        redisManager.setDatabase(0);
        return redisManager;
    }
    */
    /**
     * DAO大家都用过，数据访问对象，用于会话的CRUD，比如我们想把Session保存到数据库，那么可以实现自己的SessionDAO，
     * 通过如JDBC写到数据库；比如想把Session放到Memcached中，可以实现自己的Memcached SessionDAO；
     * 另外SessionDAO中可以使用Cache进行缓存，以提高性能；
     */
    /*
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
    */
    /**
     * 缓存控制器，来管理如用户、角色、权限等的缓存的；
     * 因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能
     */
    /*
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
    */
}
