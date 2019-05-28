/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : IdentifyService.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;
import java.util.Map;

/**
 * 鉴定服务接口
 * @author panqh
 * @date 2018-11-13
 */
public interface IdentifyService extends CURDService<IdentifyMain, String> {

    /**
     * 保存鉴定申请页面
     * @author panqh
     * @date 2018-11-19
     * @param params
     * @return
     */
    void saveIdentifyRequestInfo(Map<String, Object> params);


    String getIdentifyInfo(String mainId);

    /**
     * 更新鉴定主表状态
     * @param identifyStatusCk
     */
    void updateMainStatus(String id, Integer identifyStatusCk);

    /**
     * 更新鉴定主表流程状态
     * @param processStatus (0: 进行中；1：已挂起；2：已终止)
     */
    void updateMainProcessStatus(String id, Integer processStatus);

    /**
     * 签收任务
     * @param mainId
     * @param userId
     */
    void claim(String mainId, String userId);

    /**
     * 获取taskId
     * @param mainId
     * @param userId
     * @return
     */
    String getTaskId(String mainId, String userId);


    /**
     * 获取流程实例ID
     * @param mainId
     * @param userId
     * @return
     */
    String getInstanceId(String mainId, String userId);

    /**
     * 终止流程
     * @param params
     */
    void stopSuspendOrResumeProcess(Map<String, Object> params);

    /**
     * 根据鉴定主键获取鉴定危房信息
     * @author panqh
     * @date 2018-12-12
     * @param identifyId
     * @return
     */
    String getIdentifyDangerHouseDetail(String identifyId);



}
