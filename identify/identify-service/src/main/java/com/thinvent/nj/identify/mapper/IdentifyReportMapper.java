package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyReport;

/**
* T_IDENTIFY_REPORT Mapper
* @author wangwj
* @date 2018-11-21
*/
@Repository
public interface IdentifyReportMapper extends CURDMapper<IdentifyReport, String> {

    /**
     * 根据主表ID查看鉴定报告编制表
      */

    List<IdentifyReport> getIdentifyReportByMainId(String mainId);
}
