package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyAccept;
import com.thinvent.nj.identify.entity.IdentifyPreview;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.Map;

/**
* T_IDENTIFY_PREVIEW服务
* @author administrator
* @date 2018-11-21
*/
public interface IdentifyPreviewService extends CURDService<IdentifyPreview, String> {

    /**
     * 保存初勘信息
     * @param params
     */
    void insert(Map<String, Object> params);

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取初勘信息.列表详细用
     *
     */
    IdentifyPreview getPreviewByMainId(String mainId);
}
