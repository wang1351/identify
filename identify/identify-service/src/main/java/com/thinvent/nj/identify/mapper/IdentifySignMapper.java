package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifySign;

/**
* T_IDENTIFY_SIGN Mapper
* @author wangwj
* @date 2018-11-21
*/
@Repository
public interface IdentifySignMapper extends CURDMapper<IdentifySign, String> {
    /**
     * 根据主表ID查看报告签发表
     * @param mainId
     * @return
     */

   IdentifySign getIdentifySignByMainId(String mainId);

}
