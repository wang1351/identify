package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyReport;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;
import java.util.Map;

/**
* T_IDENTIFY_REPORT服务
* @author liupj
* @date 2018-11-21
*/
public interface IdentifyReportService extends CURDService<IdentifyReport, String> {

    /**
     * 保存编制鉴定报告信息
     * @param params
     */
    void insert(Map<String, Object> params);


    /**
     * 根据鉴定业务主ID获取鉴定报告
     * @param mainId
     * @return
     */
    IdentifyReport getByMainId(String mainId);
}
