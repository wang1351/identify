/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : HouseSplitFile.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * 分栋信息附件实体
 * @author panqh
 * @date 2018-11-13
 */
public class HouseSplitFile extends BaseOracleEntity {

    /**
     * 分栋信息ID
     */
    private String splitId;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 类型（1: 图纸材料）
     */
    private Integer type;

    /**
     * 排序号
     */
    private Integer sort;


    public String getSplitId() {
        return splitId;
    }

    public void setSplitId(String splitId) {
        this.splitId = splitId;
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
