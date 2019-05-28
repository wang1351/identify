package com.thinvent.nj.identify.conf;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.thinvent.nj.identify.entity.IdentifyApply;
import com.thinvent.nj.identify.service.*;
import com.thinvent.nj.mybatis.conf.AbstractDubboConfig;
import com.thinvent.nj.supervise.service.SuperviseService;
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

    @Value("${dubbo.supervise.version}")
    private String superviseVersion;

    @Bean
    public ReferenceBean<OrgService> r1() { return ref(OrgService.class, ucVersion); }
    @Bean
    public ReferenceBean<UserService> r2() { return ref(UserService.class, ucVersion); }
    @Bean
    public ReferenceBean<DictService> r3() { return ref(DictService.class, ucVersion); }
    @Bean
    public ReferenceBean<SuperviseService> r6() { return ref(SuperviseService.class, superviseVersion); }


    @Bean
    public ServiceBean<MessageService> s11(MessageService s) { return service(s, version); }

    @Bean
    public ServiceBean<IdentifyOrgService> s12(IdentifyOrgService s) { return service(s, version); }
    @Bean
    public ServiceBean<CreditGradeService> s13(CreditGradeService s) { return service(s, version); }
    @Bean
    public ServiceBean<ExpertService> s14(ExpertService s) { return service(s, version); }
    @Bean
    public ServiceBean<ScoreRuleService> s15(ScoreRuleService s) { return service(s, version); }
    @Bean
    public ServiceBean<OrgCreditService> s16(OrgCreditService s) { return service(s, version); }
    @Bean
    public ServiceBean<IdentifyService> s17(IdentifyService s) { return service(s, version); }
    @Bean
    public ServiceBean<IdentifyMainService> s18(IdentifyMainService s) { return service(s, version); }
    @Bean
    public ServiceBean<IdentifyAcceptService>s19(IdentifyAcceptService s){return service(s, version);}
    @Bean
    public ServiceBean<ReviewService>s20(ReviewService s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifyPreviewService>s21(IdentifyPreviewService s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifyContractService>s22(IdentifyContractService s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifyPlanService>s23(IdentifyPlanService  s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifyReportService>s24(IdentifyReportService s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifySendService>s25(IdentifySendService s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifySignService>s26(IdentifySignService s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifyVerifyService>s27(IdentifyVerifyService s){return service(s, version);}
    @Bean
    public ServiceBean<ReviewMainService>s28(ReviewMainService s){return service(s, version);}
    @Bean
    public ServiceBean<CorrectService>s29(CorrectService s){return service(s, version);}
    @Bean
    public ServiceBean<CorrectMainService>s30(CorrectMainService s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifyRecordService>s31(IdentifyRecordService s){return service(s, version);}
    @Bean
    public ServiceBean<CorrectRecordService>s32(CorrectRecordService s){return service(s, version);}
    @Bean
    public ServiceBean<ReviewRecordService>s33(ReviewRecordService s){return service(s, version);}
    @Bean
    public ServiceBean<IdentifyOrgDeviceService> s34(IdentifyOrgDeviceService s) {return service(s, version);}
    @Bean
    public ServiceBean<IdentifyOrgIdentifyService>s35(IdentifyOrgIdentifyService s){return service(s,version);}
    @Bean
    public ServiceBean<IdentifyOrgPersonService>s36(IdentifyOrgPersonService s){return service(s,version);}
    @Bean
    public ServiceBean<IdentifyOrgResumeService>s37(IdentifyOrgResumeService s){return service(s,version);}
    @Bean
    public ServiceBean<IdentifyApplyService>s38(IdentifyApplyService s){return service(s,version);}

}
