package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.entity.IdentifyRecord;
import com.thinvent.nj.identify.mapper.IdentifyRecordMapper;
import com.thinvent.nj.identify.service.IdentifyRecordService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * T_IDENTIFY_RECORD服务实现
 *
 * @author administrator
 * @date 2018-11-21
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IdentifyRecordServiceImpl extends BaseCURDServiceImpl<IdentifyRecord, String> implements IdentifyRecordService {

    @Autowired
    private IdentifyRecordMapper recordMapper;

    /**
     * 插入流程记录表
     * @param params {
     *      mainId，
     *      curUserId，
     *      curFullName，
     *      status,
     *      businessKey,
     *      remarks
     * }
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {

        String curFullName = (String)params.get("curFullName");
        String curUserId = (String)params.get("curUserId");

        IdentifyRecord record = new IdentifyRecord();
        MapperUtil.copy(params, record);

        record.setOperatorUserName(curFullName);
        record.setOperatorTime(new Date());
        record.setCreateUsername(curFullName);
        record.setCreateUser(curUserId);
        record.setCreateTime(new Date());
        record.setSort(recordMapper.generatorNextSort(record.getMainId()));

        recordMapper.insert(record);
    }

    @Override
    public List<IdentifyRecord> getListByCondition(Map<String, Object> map) {
        return recordMapper.findList(map);
    }


}


