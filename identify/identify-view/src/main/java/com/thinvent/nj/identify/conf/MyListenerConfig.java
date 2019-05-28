package com.thinvent.nj.identify.conf;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * listener config
 */
@Configuration
public class MyListenerConfig {

    @Bean
    public ServletListenerRegistrationBean singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new SingleSignOutHttpSessionListener());
        return registrationBean;
    }
}
