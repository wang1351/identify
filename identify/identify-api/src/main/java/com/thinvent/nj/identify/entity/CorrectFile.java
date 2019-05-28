/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectFile.java
 * 功能概要       :
 * 做成日期       : 2018-12-05・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
* 补正变更业务附件
* @author panqh
* @date 2018-12-05
*/
public class CorrectFile extends BaseOracleEntity {

    // 排序
    private Integer sort;

    // 文件ID
    private String fileId;

    // 补正业务表ID
    private String correctId;



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

    public String getCorrectId() {
        return correctId;
    }

    public void setCorrectId(String correctId) {
        this.correctId = correctId;
    }
}
