/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : IdentifyOrgType.java
 * 功能概要       :
 * 做成日期       : 2018-11-01・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * 鉴定机构业务类型实体
 * @author panqh
 * @date 2018-11-01
 */
public class IdentifyOrgType extends BaseOracleEntity {

    /**
     * 鉴定机构ID (FK -> T_IDENTIFY_ORG.ID)
     */
    private String identifyOrgId;

    /**
     * 鉴定业务类型KEY(FK -> T_BASE_DICT_ITEM.KEY)
     */
    private String identifyTypeKey;
    private String name;


    public String getIdentifyOrgId() {
        return identifyOrgId;
    }

    public void setIdentifyOrgId(String identifyOrgId) {
        this.identifyOrgId = identifyOrgId;
    }

    public String getIdentifyTypeKey() {
        return identifyTypeKey;
    }

    public void setIdentifyTypeKey(String identifyTypeKey) {
        this.identifyTypeKey = identifyTypeKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
