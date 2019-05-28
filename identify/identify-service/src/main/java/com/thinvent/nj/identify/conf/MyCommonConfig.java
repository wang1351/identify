package com.thinvent.nj.identify.conf;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCommonConfig {
    /**
     * exportCache
     * @return
     */
    @Bean(name = "exportCache")
    public Cache exportCache(CacheManager cacheManager) {
        return cacheManager.getCache("export");
    }

    /**
     * orgCreditCache
     * @return
     */
    @Bean(name = "orgCreditCache")
    public Cache orgCreditCache(CacheManager cacheManager) {
        return cacheManager.getCache("orgCredit");
    }
}
