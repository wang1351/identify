package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyPlan;
import com.thinvent.nj.identify.entity.IdentifyPreview;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.Map;

/**
* T_IDENTIFY_PLAN服务
* @author administrator
* @date 2018-11-21
*/
public interface IdentifyPlanService extends CURDService<IdentifyPlan, String> {

    /**
     * 保存出具方案信息
     * @param params
     */
    void insert(Map<String, Object> params);
    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取出具方案信息.列表详细用
     *
     */
    IdentifyPlan getPlanByMainId(String mainId);

}
