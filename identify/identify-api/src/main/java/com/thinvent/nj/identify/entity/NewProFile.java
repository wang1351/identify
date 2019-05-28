/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : NewProFile.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * 新建工程附件实体
 * @author panqh
 * @date 2018-11-13
 */
public class NewProFile extends BaseOracleEntity {

    /**
     * 房屋新建工程表ID（FK -> T_IDENTIFY_NEW_PROJECT.ID）
     */
    private String newProjectId;

    /**
     * 文件ID（FK -> T_BASE_FILE.ID）
     */
    private String fileId;

    /**
     * 类型（1: 房屋安全鉴定委托书附表；
            2: 岩石工程勘察报告；
            3: 新建工程建筑及结构图纸；
            4: 新建工程基坑支护方案；
            5: 所需鉴定房屋建筑及结构图纸）
     */
    private Integer type;

    /**
     * 排序号
     */
    private Integer sort;


    public String getNewProjectId() {
        return newProjectId;
    }

    public void setNewProjectId(String newProjectId) {
        this.newProjectId = newProjectId;
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
