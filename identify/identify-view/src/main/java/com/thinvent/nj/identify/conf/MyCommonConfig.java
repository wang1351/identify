package com.thinvent.nj.identify.conf;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCommonConfig {
    /**
     * shiroSessionCache
     * @return
     */
    @Bean(name = "shiroSessionCache")
    public Cache shiroSessionCache(CacheManager cacheManager) {
        return cacheManager.getCache("shiro-sessionCache");
    }




}
