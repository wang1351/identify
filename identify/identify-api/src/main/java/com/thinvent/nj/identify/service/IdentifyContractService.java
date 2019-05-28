package com.thinvent.nj.identify.service;

import com.thinvent.nj.identify.entity.IdentifyContract;
import com.thinvent.nj.identify.entity.IdentifySign;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.Map;

/**
* T_IDENTIFY_CONTRACT服务
* @author administrator
* @date 2018-11-21
*/
public interface IdentifyContractService extends CURDService<IdentifyContract, String> {

    /**
     * 保存签订合同信息
     * @param params
     */
    void insert(Map<String, Object> params);

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取签订合同信息.列表详细用
     *
     */
    IdentifyContract getContractByMainId(String mainId);
}
