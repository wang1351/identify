package com.thinvent.nj.identify.activiti;

import com.thinvent.nj.mybatis.service.impl.ConcreteProcessService;
import org.springframework.stereotype.Component;

/**
 * 激活主流程服务实现类
 * @author liupeijun
 */
@Component
public class ActiveProcessService extends ConcreteProcessService {

    /**
     * 鉴定业务流程-激活流程（实现鉴定业务的激活功能）
     */
    protected String getProcessDefinitionKey() {
        return "process-identify-active";
    }

}
