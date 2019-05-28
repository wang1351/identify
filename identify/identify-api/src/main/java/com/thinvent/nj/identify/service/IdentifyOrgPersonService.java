package com.thinvent.nj.identify.service;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.identify.entity.IdentifyOrgMain;
import com.thinvent.nj.identify.entity.IdentifyOrgPerson;
import com.thinvent.nj.mybatis.service.CURDService;

/**
* 鉴定机构法定代表人及技术负责人
* @author wangwj
* @date 2019-3-18
*/
public interface IdentifyOrgPersonService extends CURDService<IdentifyOrgPerson, String> {
    /**
     * 保存法定代表人及技术负责人
     */

    public void saveIdentifyOrgPerson(IdentifyOrgPerson identifyOrgPerson);











}
