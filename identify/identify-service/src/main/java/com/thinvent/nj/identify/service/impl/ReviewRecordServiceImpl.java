package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.entity.IdentifyRecord;
import com.thinvent.nj.identify.entity.IdentifyReviewRecord;
import com.thinvent.nj.identify.entity.Review;
import com.thinvent.nj.identify.mapper.IdentifyRecordMapper;
import com.thinvent.nj.identify.mapper.IdentifyReviewRecordMapper;
import com.thinvent.nj.identify.mapper.ReviewMapper;
import com.thinvent.nj.identify.service.IdentifyRecordService;
import com.thinvent.nj.identify.service.ReviewRecordService;
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
public class ReviewRecordServiceImpl extends BaseCURDServiceImpl<IdentifyReviewRecord, String> implements ReviewRecordService {

    @Autowired
    private IdentifyReviewRecordMapper recordMapper;
    @Autowired
    private ReviewMapper reviewMapper;


    @Override
    public List<IdentifyReviewRecord> getListByCondition(Map<String, Object> map) {
       // update by xujc 2019/1/21 start
        String mainId = (String)map.get("mainId");
        // 我的待办里直接传reviewId，鉴定详细里传的是mainId 需要找到reviewId
        if(!StringUtil.isNullOrEmpty(mainId)){
            Review review = reviewMapper.getByMainId(mainId);
            map.put("reviewId",review.getId());
        }
        // update by xujc 2019/1/21 end
        return recordMapper.findList(map);
    }


}


