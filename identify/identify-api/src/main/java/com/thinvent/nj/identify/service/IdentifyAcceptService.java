package com.thinvent.nj.identify.service;

import com.thinvent.nj.mybatis.service.CURDService;
import com.thinvent.nj.identify.entity.IdentifyAccept;

import java.util.List;
import java.util.Map;

/**
* T_IDENTIFY_ACCEPT服务
* @author administrator
* @date 2018-11-21
*/
public interface IdentifyAcceptService extends CURDService<IdentifyAccept, String> {
    /**
     * 保存受理信息
     * @param params
     */
    void insert(Map<String, Object> params);

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取受理信息.列表详细用
     *
     */
    IdentifyAccept getAcceptByMainId(String mainId);
}
