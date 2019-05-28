package com.thinvent.nj.identify.activiti;

import com.thinvent.nj.mybatis.service.impl.ConcreteProcessService;
import org.springframework.stereotype.Component;

/**
 * 补正变更流程服务实现类
 * @author panqh
 * @date 2018-12-05
 */
@Component
public class CorrectProcessService extends ConcreteProcessService {

    /**
     * 鉴定业务流程-复审
     */
    @Override
    protected String getProcessDefinitionKey() {
        return "process-identify-correct";
    }
}
