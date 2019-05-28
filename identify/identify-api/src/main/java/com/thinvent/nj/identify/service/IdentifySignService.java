package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifySign;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.Map;

/**
 * 鉴定报告签发服务
 *
 * @author liupj
 * @date 2018-11-21
 */
public interface IdentifySignService extends CURDService<IdentifySign, String> {

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取鉴定报告签发信息.列表详细用
     *
     */
    IdentifySign getSignByMainId(String mainId);
    /**
     * 保存报告签发信息
     *
     * @param params
     */
    void insert(Map<String, Object> params);
}
