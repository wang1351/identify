package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.IdentifyReviewRecord;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

/**
* IdentifyReviewRecordMapper
* @author xujc
* @date 2018-12-14
*/
@Repository
public interface IdentifyReviewRecordMapper extends CURDMapper<IdentifyReviewRecord, String> {

    /**
     * 为指定reviewId生成下一个序号
     * @param reviewId
     * @return
     */
    Integer generatorNextSort(String reviewId);

}




