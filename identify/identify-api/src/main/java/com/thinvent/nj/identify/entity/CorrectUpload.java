package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * @author : xujc
 * @date :2018/12/17
 * @Description :  补正资料上传表
 */
public class CorrectUpload extends BaseOracleEntity {
    // 补正业务表ID（FK -> T_IDENTIFY_CORRECT.ID）
    private String correctId;
    // 补正说明
    private String description;

    public String getCorrectId() {
        return correctId;
    }

    public void setCorrectId(String correctId) {
        this.correctId = correctId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
