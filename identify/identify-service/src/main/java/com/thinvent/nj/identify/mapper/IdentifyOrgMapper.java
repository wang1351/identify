package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyOrgMain;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

import com.thinvent.nj.identify.entity.IdentifyOrg;

import java.util.List;
import java.util.Map;

/**
* 鉴定机构名录库 Mapper
* @author panqh
* @date 2018-11-01
*/
@Repository
public interface IdentifyOrgMapper extends CURDMapper<IdentifyOrg, String> {

    /**
     * 鉴定机构的启用与禁用
     * @author panqh
     * @date 2018-11-02
     * @param identifyOrg
     */
    void OrgUseOrUnUse(IdentifyOrg identifyOrg);

    /**
     * 根据条件获取鉴定机构列表
     * @author panqh
     * @date 2018-11-23
     * @param condition
     * @return
     */
    List<IdentifyOrg> getIdentifyOrgListByCondition(Map<String, Object> condition);

}
