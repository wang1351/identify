/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewExpert.java
 * 功能概要       :
 * 做成日期       : 2018-11-28・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
 * 复核业务专家
 *
 * @author administrator
 * @date 2018-11-28
 */
public class ReviewExpert extends BaseOracleEntity {

    // 鉴定复核主表ID
    private String reviewId;

    // 选择方式（1: 手动；2:随机）
    private Integer method;
    // 类型（1: 选择专家；2:确认专家）
    private Integer type;
    // 专家领域
    private Integer expertField;
    // 专家id集合
    private String expertIds;



    // 专家领域名称
    private String expertFieldName;


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


    public String getExpertFieldName() {
        return expertFieldName;
    }

    public void setExpertFieldName(String expertFieldName) {
        this.expertFieldName = expertFieldName;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExpertIds() {
        return expertIds;
    }

    public void setExpertIds(String expertIds) {
        this.expertIds = expertIds;
    }
}
