/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewSend.java
 * 功能概要       :
 * 做成日期       : 2018-12-04・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* 复核业务办结
* @author panqh
* @date 2018-12-04
*/
public class ReviewSend extends BaseOracleEntity {

    // 操作时间
    private Date operatorTime;

    // 操作人
    private String operatorUserName;

    // 复核业务主表ID
    private String reviewId;



    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatorUserName() {
        return operatorUserName;
    }

    public void setOperatorUserName(String operatorUserName) {
        this.operatorUserName = operatorUserName;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
