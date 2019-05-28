package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyContract;

/**
* T_IDENTIFY_CONTRACT Mapper
* @author administrator
* @date 2018-11-21
*/
@Repository
public interface IdentifyContractMapper extends CURDMapper<IdentifyContract, String> {
    /**
     * 根据主表id查询签订合同表
     * @param mainId
     */
    IdentifyContract getIdentifyContractByMainId(String mainId);
}
