package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifySign;
import com.thinvent.nj.identify.entity.IdentifyVerify;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.Map;

/**
* 鉴定报告审核 服务
* @author liupj
* @date 2018-11-21
*/
public interface IdentifyVerifyService extends CURDService<IdentifyVerify, String> {
    /**
     * 保存报告审核信息
     * @param params
     */
    void insert(Map<String, Object> params);

}
