
package com.thinvent.nj.identify.conf;

import com.google.common.collect.Maps;
import com.thinvent.nj.shiro.MyCredentialsMatcher;
import com.thinvent.nj.shiro.RedisCacheManager;
import com.thinvent.nj.shiro.StatelessSessionManager;
import com.thinvent.nj.uc.shiro.MyRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * @author liupj
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm myRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(new MyCredentialsMatcher());

        return myRealm;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }


    @Bean("securityManager")
    public SecurityManager securityManager(Realm realm, CacheManager cacheManager, SessionManager sessionManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setSubjectFactory(new CasSubjectFactory());
        manager.setRealm(realm);
        manager.setCacheManager(cacheManager);
        manager.setSessionManager(sessionManager);

        return manager;
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO) {
        StatelessSessionManager sessionManager = new StatelessSessionManager();
        sessionManager.setGlobalSessionTimeout(3600 * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(false);

        sessionManager.setSessionDAO(sessionDAO);

        return sessionManager;
    }







    @Bean
    public SessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-sessionCache");

        return sessionDAO;
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterBean(SecurityManager securityManager,
                                                  Environment env) {
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
        factory.setSecurityManager(securityManager);
        factory.setLoginUrl("/login");

        Map<String, String> chains = Maps.newHashMap();
        chains.put("/login/**", "anon");
        chains.put("/", "anon");
        chains.put("/index", "anon");
        chains.put("/error/**", "anon");
        chains.put("/identify/orgs", "anon");
        chains.put("/favicon.ico", "anon");
        chains.put("/actuator/**", "anon");
        chains.put("/**/swagger-ui.html", "anon");
        chains.put("/swagger-ui.html", "anon");
        chains.put("/**/api-docs", "anon");
        chains.put("/webjars/**", "anon");
        chains.put("/swagger-resources/**", "anon");
        chains.put("/internet/**", "anon");




        chains.put("/**", "authc");

        factory.setFilterChainDefinitionMap(chains);

       return factory;
    }

    /**
     * Shiro生命周期处理器 * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和
     * AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
