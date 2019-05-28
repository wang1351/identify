package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyAccept;

/**
* T_IDENTIFY_ACCEPT Mapper
* @author wangwj
* @date 2018-11-21
*/
@Repository
public interface IdentifyAcceptMapper extends CURDMapper<IdentifyAccept, String> {
    /**
     * 通过主业务表ID查询业务受理表
     * @param mainId
     * @return
     */
    IdentifyAccept getIdentifyAcceptByMainId(String mainId);


}
