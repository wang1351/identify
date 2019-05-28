package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * @author : xujc
 * @date :2018/11/8
 * @Description : 专家领域实体
 */
public class ExpertBusinessArea extends BaseOracleEntity {
    // 专家id
    private String expertId;
    // 专家领域代号值
    private Integer expertField;
    // 专家领域名称
    private  String expertBusinessArea;

    public String getExpertId() {
        return expertId;
    }



    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public Integer getExpertField() {
        return expertField;
    }

    public void setExpertField(Integer expertField) {
        this.expertField = expertField;
    }
    public String getExpertBusinessArea() {
        return expertBusinessArea;
    }

    public void setExpertBusinessArea(String expertBusinessArea) {
        this.expertBusinessArea = expertBusinessArea;
    }

}
