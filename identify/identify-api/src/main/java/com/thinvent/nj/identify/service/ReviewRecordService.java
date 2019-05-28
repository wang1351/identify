package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyReviewRecord;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;
import java.util.Map;

/**
* T_IDENTIFY_REVIEW_RECORD服务
* @author xujc
* @date 2019-01-03
*/
public interface ReviewRecordService extends CURDService<IdentifyReviewRecord, String> {


    /**
     * @author : xujc
     * @date :2018/12/10
     * @Description : 根据条件找到记录对象集合
     */
    List<IdentifyReviewRecord> getListByCondition(Map<String, Object> map);


}
