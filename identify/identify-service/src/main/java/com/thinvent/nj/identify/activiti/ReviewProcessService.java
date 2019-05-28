package com.thinvent.nj.identify.activiti;

import com.thinvent.nj.mybatis.service.impl.ConcreteProcessService;
import org.springframework.stereotype.Component;

/**
 * 复审流程服务实现类
 * @author liupeijun
 */
@Component
public class ReviewProcessService extends ConcreteProcessService {

    /**
     * 鉴定业务流程-复审
     */
    @Override
    protected String getProcessDefinitionKey() {
        return "process-identify-review";
    }
}
