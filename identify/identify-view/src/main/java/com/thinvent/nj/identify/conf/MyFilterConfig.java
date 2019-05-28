package com.thinvent.nj.identify.conf;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * listener config
 * @author liupeijun
 */
@Configuration
public class MyFilterConfig {

    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new SingleSignOutFilter());

        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(10);
        return registrationBean;
    }





}
