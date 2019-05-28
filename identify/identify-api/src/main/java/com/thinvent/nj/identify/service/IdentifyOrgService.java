package com.thinvent.nj.identify.service;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.mybatis.service.CURDService;

import java.util.List;
import java.util.Map;

/**
* 鉴定机构名录库服务
* @author panqh
* @date 2018-11-01
*/
public interface IdentifyOrgService extends CURDService<IdentifyOrgMain, String> {
    //update by wangwj 20190306 start
    /**
     * 保存鉴定机构信息
     * @author panqh
     * @date 2018-11-23
     * @param identifyOrg
     * @return
     */
    IdentifyOrgMain saveIdentifyOrg(IdentifyOrgMain identifyOrg);

    /**
     * 保存所有的鉴定机构信息
     * @author wangwj
     * @date 2019-3-18
     */
    public void saveIdentifyOrgList(Map<String, Object> params);


    /**
     * 保存鉴定机构鉴定类型
     * @author panqh
     * @date 2018-11-02
     * @param identifyOrgId
     * @param typeArray
     */
    void saveIdentifyOrgType(String identifyOrgId, JSONArray typeArray);

    /**
     * 鉴定机构的启用与禁用
     * @author panqh
     * @date 2018-11-02
     * @param type
     * @param idS
     */
    void identifyOrgUseOrUnUse(String type, String idS);


    /**
     * 根据行政审批平台ORG_ID获取鉴定机构名录库信息
     * @param platfromId
     * @return
     */
    public IdentifyOrgMain getOrgMainByPlatformId(String platfromId);

    /**
     * @author : xujc
     * @date :2019/4/8
     * @Description : 根据鉴定类型找到鉴定机构信息(H5用)
     */
    public List<IdentifyOrgMain> getIdentifyOrgsByTypeId(String identifyTypeId);











    //update by wangwj 20190306 end
}
