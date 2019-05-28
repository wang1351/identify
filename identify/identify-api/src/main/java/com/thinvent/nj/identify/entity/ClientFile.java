/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ClientFile.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * 委托人附件实体
 * @author panqh
 * @date 2018-11-13
 */
public class ClientFile extends BaseOracleEntity {

    /**
     * 委托人信息表ID
     */
    private String clientId;

    /**
     * 附件ID
     */
    private String fileId;

    /**
     * 类型（1: 房屋产权证； 2: 身份证； 3: 其他）
     */
    private Integer type;

    /**
     * 排序号
     */
    private Integer sort;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
