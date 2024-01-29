package com.example.forum.config;

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
import com.example.forum.shiro.MyRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置Shiro安全框架的Spring Boot组件
 */
@Slf4j
@Component
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(realm);
        log.info("securityManager -----------> 初始化了");
        return securityManager;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


//        //自定义过滤器
//        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
//        // 注意这里不要用Bean的方式，否则会报错
//        filters.put("myRole", new MyRoleFilter());
//        shiroFilterFactoryBean.setFilters(filters);


        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");



        Map<String, String> hashMap = new LinkedHashMap<>();

//        hashMap.put("/**", "authc");
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
//        hashMap.put("/auth", "roles[user]");
        hashMap.put("/signIn", "anon");
        hashMap.put("/signOut", "anon");
        hashMap.put("/signUp", "anon");
        hashMap.put("/signUp/**", "anon");


        hashMap.put("/**", "anon");



        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 开启aop注解支持
     * 即在controller中使用 @RequiresPermissions("user/userList")
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        //设置安全管理器
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

//    /**
//     * 处理未授权的异常，返回自定义的错误页面（403）
//     * @return
//     */
//    @Bean
//    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
//        Properties properties = new Properties();
//        /*未授权处理页*/
//        properties.setProperty("UnauthorizedException", "classpath:403.html");
//        resolver.setExceptionMappings(properties);
//        return resolver;
//    }





    // shiro-redis
    //====== session共享 ========
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
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
