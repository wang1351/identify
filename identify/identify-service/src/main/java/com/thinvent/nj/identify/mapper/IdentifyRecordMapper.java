package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.entity.IdentifyRecord;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* T_IDENTIFY_Record Mapper
* @author xujc
* @date 2018-12-10
*/
@Repository
public interface IdentifyRecordMapper extends CURDMapper<IdentifyRecord, String> {

    /**
     * 为指定mainId生成下一个序号
     * @param mainId
     * @return
     */
    Integer generatorNextSort(String mainId);

}




