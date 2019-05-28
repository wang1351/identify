package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
* T_IDENTIFY_PREVIEW_FILE
* @author wangwj
* @date 2018-11-21
*/
public class IdentifyPreviewFile extends BaseOracleEntity {

    // 初勘表ID
    private String previewId;

    // 排序号
    private Integer sort;

    // 类型
    private Integer type;

    // 文件ID
    private String fileId;


    public String getPreviewId() {
        return previewId;
    }

    public void setPreviewId(String previewId) {
        this.previewId = previewId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
