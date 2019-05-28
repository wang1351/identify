package com.thinvent.nj.identify.entity;
import com.thinvent.nj.mybatis.entity.BaseOracleEntity;
/**
 * @author : xujc
 * @date :2018/12/25
 * @Description : 复核受理
 */
public class ReviewAccept extends BaseOracleEntity {
    private String reviewId;
    private String reason;
    // 0不通过 1通过
    private Integer result;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
