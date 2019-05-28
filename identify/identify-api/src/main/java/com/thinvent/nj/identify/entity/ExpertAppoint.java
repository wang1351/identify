/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ExpertAppoint.java
 * 功能概要       :
 * 做成日期       : 2018-11-28・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
* 复核业务指定专家
* @author panqh
* @date 2018-11-28
*/
public class ExpertAppoint extends BaseOracleEntity {

    // 鉴定复核主表ID
    private String reviewId;

    // 专家领域
    private Integer expertField;
    // 专家领域名称
    private String expertFieldName;

    // 专家数量
    private Integer expertNo;



    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getExpertField() {
        return expertField;
    }

    public void setExpertField(Integer expertField) {
        this.expertField = expertField;
    }

    public Integer getExpertNo() {
        return expertNo;
    }

    public void setExpertNo(Integer expertNo) {
        this.expertNo = expertNo;
    }

    public String getExpertFieldName() {
        return expertFieldName;
    }

    public void setExpertFieldName(String expertFieldName) {
        this.expertFieldName = expertFieldName;
    }
}
