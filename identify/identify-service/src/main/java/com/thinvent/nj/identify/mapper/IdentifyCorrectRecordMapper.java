package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyCorrectRecord;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
* IdentifyCorrectRecordMapper
* @author xujc
* @date 2018-12-14
*/
@Repository
public interface IdentifyCorrectRecordMapper extends CURDMapper<IdentifyCorrectRecord, String> {

    /**
     * 为指定correctId生成下一个序号
     * @param correctId
     * @return
     */
    Integer generatorNextSort(String correctId);

}




