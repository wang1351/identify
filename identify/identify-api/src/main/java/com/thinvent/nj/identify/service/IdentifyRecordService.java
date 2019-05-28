package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyContract;
import com.thinvent.nj.identify.entity.IdentifyRecord;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;
import java.util.Map;

/**
* T_IDENTIFY_RECORD服务
* @author xujc
* @date 2018-11-21
*/
public interface IdentifyRecordService extends CURDService<IdentifyRecord, String> {

    /**
     * 插入流程记录
     * @param params {
     *      mainId，
     *      curUserId，
     *      curFullName，
     *      status,
     *      businessKey,
     *      remarks
     * }
     */
    void insert(Map<String, Object> params);

    /**
     * @author : xujc
     * @date :2018/12/10
     * @Description : 根据条件找到记录对象集合
     */
    List<IdentifyRecord> getListByCondition(Map<String,Object> map);


}
