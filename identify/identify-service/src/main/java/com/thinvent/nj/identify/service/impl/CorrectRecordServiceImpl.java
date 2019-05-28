package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.entity.Correct;
import com.thinvent.nj.identify.entity.IdentifyCorrectRecord;
import com.thinvent.nj.identify.mapper.CorrectMapper;
import com.thinvent.nj.identify.mapper.IdentifyCorrectRecordMapper;
import com.thinvent.nj.identify.service.CorrectRecordService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : xujc
 * @date :2018/12/18
 * @Description : TODO
 */
@Service
public class CorrectRecordServiceImpl extends BaseCURDServiceImpl<IdentifyCorrectRecord,String> implements CorrectRecordService {
    @Autowired
    private IdentifyCorrectRecordMapper identifyCorrectRecordMapper;
    @Autowired
    private CorrectMapper correctMapper;

    @Override
    public List<IdentifyCorrectRecord> getListByCondition(Map<String, Object> map) {
        String mainId = (String)map.get("mainId");
        // 我的待办里直接传correctId，鉴定详细里传的是mainId 需要找到correctId
        if(!StringUtil.isNullOrEmpty(mainId)){
            Correct correct = correctMapper.getByMainId(mainId);
                map.put("correctId",correct.getId());
        }
        return identifyCorrectRecordMapper.findList(map);
    }

}
