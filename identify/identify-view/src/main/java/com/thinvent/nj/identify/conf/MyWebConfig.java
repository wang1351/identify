package com.thinvent.nj.identify.conf;

import com.thinvent.nj.uc.inteceptor.MenuInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MenuInteceptor menuInteceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MappedInterceptor interceptor = new MappedInterceptor(new String[] {"/**"}, menuInteceptor);
        registry.addInterceptor(interceptor);
    }
}
