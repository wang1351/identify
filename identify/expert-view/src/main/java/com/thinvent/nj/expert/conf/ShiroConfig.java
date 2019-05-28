package com.thinvent.nj.expert.conf;

import com.thinvent.nj.shiro.RedisCacheManager;
import com.thinvent.nj.uc.shiro.MyCasRealm;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liupj
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm casRealm(Environment env) {
        MyCasRealm casRealm = new MyCasRealm();
        casRealm.setCasServerUrlPrefix(env.getProperty("cas.serverUrlPrefix"));
        casRealm.setCasService(env.getProperty("cas.service"));

        return casRealm;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }


    @Bean("securityManager")
    public SecurityManager securityManager(Realm realm, RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setSubjectFactory(new CasSubjectFactory());
        manager.setRealm(realm);
        manager.setCacheManager(redisCacheManager);

        return manager;
    }


    @Bean(name = "shiroFilter")

    public ShiroFilterFactoryBean shiroFilterBean(SecurityManager securityManager,
                                                  Environment env) throws Exception {
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
        factory.setSecurityManager(securityManager);
        factory.setLoginUrl(env.getProperty("login.url"));

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("casFilter", casFilter(env));
        filters.put("logoutFilter", logoutFilter(env));
        factory.setFilters(filters);

        Map<String, String> chains = new LinkedHashMap<>();
        chains.put("/shiro-cas", "casFilter");
        chains.put("/logout", "logoutFilter");
        chains.put("/js/**", "anon");
        chains.put("/css/**", "anon");
        chains.put("/error/**", "anon");
        chains.put("/img/**", "anon");
        chains.put("/static/**", "anon");
        chains.put("/actuator/**", "anon");
        chains.put("/favicon.ico", "anon");
        chains.put("/identifies/detail", "anon");



        chains.put("/**", "authc");

        factory.setFilterChainDefinitionMap(chains);

       return factory;
    }

    /**
     * 注册DelegatingFilterProxy（Shiro）
     */
    @Bean
    @Order(20)
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    private Filter logoutFilter(Environment env) {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl(env.getProperty("logout.url"));
        return logoutFilter;
    }

    private Filter casFilter(Environment env) {
        CasFilter casFilter = new CasFilter();
        casFilter.setSuccessUrl(env.getProperty("shiro.successUrl"));
        casFilter.setFailureUrl(env.getProperty("shiro.failureUrl"));

        return casFilter;
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
