package com.thinvent.nj.expert.conf;

import com.alibaba.dubbo.config.spring.ReferenceBean;

import com.thinvent.nj.identify.entity.Review;
import com.thinvent.nj.identify.service.*;

import com.thinvent.nj.web.conf.AbstractDubboConfig;
import com.thinvent.nj.uc.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfig extends AbstractDubboConfig {

    @Value("${dubbo.version}")
    private String version;

    @Value("${dubbo.uc.version}")
    private String ucVersion;

    @Bean
    public ReferenceBean<OrgService> r1() { return ref(OrgService.class, ucVersion); }
    @Bean
    public ReferenceBean<UserService> r2() { return ref(UserService.class, ucVersion); }
    @Bean
    public ReferenceBean<RoleService> r3() { return ref(RoleService.class, ucVersion); }
    @Bean
    public ReferenceBean<ResourceService> r4() { return ref(ResourceService.class, ucVersion); }
    @Bean
    public ReferenceBean<ServerService> r5() { return ref(ServerService.class, ucVersion); }
    @Bean
    public ReferenceBean<DictService> r6() { return ref(DictService.class, ucVersion); }

    @Bean
    public ReferenceBean<MessageService> r11() { return ref(MessageService.class, version); }
    @Bean
    public ReferenceBean<ExpertService> r14() { return ref(ExpertService.class, version); }
}
