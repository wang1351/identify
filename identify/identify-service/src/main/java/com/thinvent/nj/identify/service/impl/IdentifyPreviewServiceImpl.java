package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyPreview;
import com.thinvent.nj.identify.handler.BusinessHandler;
import com.thinvent.nj.identify.handler.impl.PreviewHandler;
import com.thinvent.nj.identify.mapper.IdentifyPreviewMapper;
import com.thinvent.nj.identify.service.IdentifyPreviewService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
* 初勘服务实现
* @author liupj
* @date 2018-11-21
*/
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IdentifyPreviewServiceImpl extends BaseCURDServiceImpl<IdentifyPreview, String> implements IdentifyPreviewService {

    @Autowired
    private IdentifyPreviewMapper identifyPreviewMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {
        BusinessHandler businessHandler = new PreviewHandler(params);
        businessHandler.execute();
    }

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取初勘信息
     */
    @Override
    public IdentifyPreview getPreviewByMainId(String mainId) {
        return identifyPreviewMapper.getIdentifyPreviewByMainId(mainId);
    }

}
