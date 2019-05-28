package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyVerify;

/**
* T_IDENTIFY_VERIFY Mapper
* @author wangwj
* @date 2018-11-21
*/
@Repository
public interface IdentifyVerifyMapper extends CURDMapper<IdentifyVerify, String> {
    /**
     * 根据编制表ID 查看报告审核表
     * @param reportId
     * @return
     */

    IdentifyVerify getIdentifyVerifyByReportId(String reportId);

}
