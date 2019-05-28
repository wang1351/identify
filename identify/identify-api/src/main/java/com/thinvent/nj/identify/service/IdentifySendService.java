package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifySend;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.Map;

/**
* 鉴定报告发送服务
* @author liupj
* @date 2018-11-21
*/
public interface IdentifySendService extends CURDService<IdentifySend, String> {
    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取鉴定报告签发放信息.列表详细用
     *
     */
    IdentifySend getSendByMainId(String mainId);

    /**
     * 保存鉴定报告发送信息
     * @param params
     */
    void insert(Map<String, Object> params);
}
