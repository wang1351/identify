package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyCorrectRecord;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;
import java.util.Map;

/**
* T_IDENTIFY_RECORD服务
* @author xujc
* @date 2018-11-21
*/
public interface CorrectRecordService extends CURDService<IdentifyCorrectRecord, String> {



    /**
     * @author : xujc
     * @date :2018/12/18
     * @Description : 根据条件找到记录对象集合
     */
    List<IdentifyCorrectRecord> getListByCondition(Map<String, Object> map);


}
