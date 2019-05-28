package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyPlan;

/**
* T_IDENTIFY_PLAN Mapper
* @author administrator
* @date 2018-11-21
*/
@Repository
public interface IdentifyPlanMapper extends CURDMapper<IdentifyPlan, String> {
    /**
     * 根据主键查询出具方案表
     * @param mainId
     */
     IdentifyPlan getIdentifyPlanByMainId(String mainId);
}
