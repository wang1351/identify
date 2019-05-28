package com.thinvent.nj.identify.conf;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.thinvent.nj.identify.entity.IdentifyApply;
import com.thinvent.nj.identify.service.*;
import com.thinvent.nj.uc.service.*;
import com.thinvent.nj.web.conf.AbstractDubboConfig;
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
    public ReferenceBean<IdentifyOrgService> r12() { return ref(IdentifyOrgService.class, version); }
    @Bean
    public ReferenceBean<CreditGradeService> r13() { return ref(CreditGradeService.class, version); }
    @Bean
    public ReferenceBean<ExpertService> r14() { return ref(ExpertService.class, version); }
    @Bean
    public ReferenceBean<ScoreRuleService> r15() { return ref(ScoreRuleService.class, version); }
    @Bean
    public ReferenceBean<OrgCreditService> r16() { return ref(OrgCreditService.class, version); }
    @Bean
    public ReferenceBean<IdentifyService> r17() { return ref(IdentifyService.class, version); }
    @Bean
    public ReferenceBean<IdentifyMainService> r18() { return ref(IdentifyMainService.class, version); }
    @Bean
    public ReferenceBean<IdentifyAcceptService> r19() { return ref(IdentifyAcceptService.class, version); }
    @Bean
    public ReferenceBean<ReviewService> r20() { return ref(ReviewService.class, version); }
    @Bean
    public ReferenceBean<IdentifyPreviewService> r21() { return ref(IdentifyPreviewService.class, version); }
    @Bean
    public ReferenceBean<IdentifyContractService> r22() { return ref(IdentifyContractService.class, version); }
    @Bean
    public ReferenceBean<IdentifyPlanService> r23() { return ref(IdentifyPlanService.class, version); }
    @Bean
    public ReferenceBean<IdentifyReportService> r24() { return ref(IdentifyReportService.class, version); }
    @Bean
    public ReferenceBean<IdentifySendService> r25() { return ref(IdentifySendService.class, version); }
    @Bean
    public ReferenceBean<IdentifySignService> r26() { return ref(IdentifySignService.class, version); }
    @Bean
    public ReferenceBean<IdentifyVerifyService> r27() { return ref(IdentifyVerifyService.class, version); }
    @Bean
    public ReferenceBean<ReviewMainService> r28() { return ref(ReviewMainService.class, version); }
    @Bean
    public ReferenceBean<CorrectService> r29() { return ref(CorrectService.class, version); }
    @Bean
    public ReferenceBean<CorrectMainService> r30() { return ref(CorrectMainService.class, version); }
    @Bean
    public ReferenceBean<IdentifyRecordService> r31() { return ref(IdentifyRecordService.class, version); }
    @Bean
    public ReferenceBean<CorrectRecordService> r32() { return ref(CorrectRecordService.class, version); }
    @Bean
    public ReferenceBean<ReviewRecordService> r33() { return ref(ReviewRecordService.class, version); }
    @Bean
    public ReferenceBean<IdentifyOrgDeviceService> r34(){return ref(IdentifyOrgDeviceService.class, version);}
    @Bean
    public ReferenceBean<IdentifyOrgIdentifyService> r35(){return ref(IdentifyOrgIdentifyService.class, version);}
    @Bean
    public ReferenceBean<IdentifyOrgPersonService> r36(){return ref(IdentifyOrgPersonService.class, version);}
    @Bean
    public ReferenceBean<IdentifyOrgResumeService> r37(){return ref(IdentifyOrgResumeService.class, version);}
    @Bean
    public ReferenceBean<IdentifyApplyService> r38(){return ref(IdentifyApplyService.class, version);}
}
