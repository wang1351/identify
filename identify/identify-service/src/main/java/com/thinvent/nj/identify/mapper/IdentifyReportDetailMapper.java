package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyReportDetail;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdentifyReportDetailMapper extends CURDMapper<IdentifyReportDetail, String> {

    void insertList(List<IdentifyReportDetail> list);

    /**
     * 根据鉴定报告ID获取鉴定报告明细列表
     * @author panqh
     * @date 2018-12-20
     * @param reportId：鉴定报告主键
     * @return
     */
    List<IdentifyReportDetail> getByReportId(String reportId);
}
