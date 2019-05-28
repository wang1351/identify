package com.thinvent.nj.identify.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.SpringContextUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.entity.IdentifyPlan;
import com.thinvent.nj.identify.enums.RecordStatus;
import com.thinvent.nj.identify.handler.AbstractBusinessHandler;
import com.thinvent.nj.identify.mapper.IdentifyPlanMapper;

import java.util.Date;
import java.util.Map;


public class PlanHandler extends AbstractBusinessHandler {

    private IdentifyPlanMapper identifyPlanMapper = SpringContextUtil.getBean(IdentifyPlanMapper.class);

    public PlanHandler(Map<String, Object> params) {
        super(params);
        mainId = (String) params.get("mainId");
    }

    @Override
    protected void save() {

        // 保存出具方案
        savePlan();

        // 更新鉴定主表
        updateIdentifyMain();
    }

    @Override
    protected void record() {
        internelRecord(RecordStatus.PLAN);
    }

    @Override
    protected void process() {
        completeTask(null);
    }

    private void savePlan() {
        // 出具方案表
        IdentifyPlan plan = new IdentifyPlan();
        MapperUtil.copy(params, plan);
        plan.setCreateUsername(curFullName);
        plan.setCreateUser(curUserId);
        plan.setCreateTime(new Date());

        // 方案文件ID
        JSONArray planFiles = (JSONArray) params.get("planFile");
        plan.setFileId(planFiles.getString(0));

        identifyPlanMapper.insert(plan);
    }


    private void updateIdentifyMain() {
        // 更新主表状态 -> 已出具方案
        IdentifyMain identifyMain = identifyService.get(mainId);
        identifyMain.setStatus(Constant.IDENTIFY_STATUS_FA);
        identifyMain.setOperatorTime(new Date());
        identifyService.update(identifyMain);
    }

}
