package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyPlan;
import com.thinvent.nj.identify.handler.impl.PlanHandler;
import com.thinvent.nj.identify.mapper.IdentifyPlanMapper;
import com.thinvent.nj.identify.service.IdentifyPlanService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * T_IDENTIFY_PLAN服务实现
 *
 * @author administrator
 * @date 2018-11-21
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IdentifyPlanServiceImpl extends BaseCURDServiceImpl<IdentifyPlan, String> implements IdentifyPlanService {

    @Autowired
    private IdentifyPlanMapper identifyPlanMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {
        new PlanHandler(params).execute();
    }
    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取出具方案信息
     */
    @Override
    public IdentifyPlan getPlanByMainId(String mainId) {
        return identifyPlanMapper.getIdentifyPlanByMainId(mainId);
    }
}
