package com.thinvent.nj.identify.activiti;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.common.util.HttpUtil;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.mybatis.service.impl.CommonProcessService;
import com.thinvent.nj.mybatis.service.impl.ConcreteProcessService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 鉴定主流程服务实现类
 * @author liupeijun
 */
@Component
public class MainProcessService extends ConcreteProcessService {

    /**
     * 鉴定业务流程-主流程
     */
    protected String getProcessDefinitionKey() {
        return "process-identify-main";
    }

}
