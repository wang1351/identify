/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : OpinionFile.java
 * 功能概要       :
 * 做成日期       : 2018-12-04・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
* 复审业务专家意见附件
* @author panqh
* @date 2018-12-04
*/
public class OpinionFile extends BaseOracleEntity {

    // 排序号
    private Integer sort;

    // 文件ID
    private String fileId;

    // 专家意见表ID
    private String opinionId;



    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(String opinionId) {
        this.opinionId = opinionId;
    }
}
