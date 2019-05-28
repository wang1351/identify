package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifySend;

/**
* T_IDENTIFY_SEND Mapper
* @author wangwj
* @date 2018-11-21
*/
@Repository
public interface IdentifySendMapper extends CURDMapper<IdentifySend, String> {
    /**
     * 通过主表ID查询报告发放表
     * @param mainId
     * @return
     */

    IdentifySend getIdentifySendByMainId(String mainId);

}
