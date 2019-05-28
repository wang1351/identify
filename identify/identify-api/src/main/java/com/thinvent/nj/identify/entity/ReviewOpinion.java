package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;
import java.util.List;

/**
 * @author : xujc
 * @date :2019/1/2
 * @Description : 复核专家意见实体
 */
public class ReviewOpinion extends BaseOracleEntity {
    private String reviewId;
    private Date meetingTime;
    private String address;
    // 参会部门
    private String deptName;
    // 参会专家
    private String experts;
    // 其他参会人员
    private String others;
    // 复核意见
    private String decision;
    // 复核结论
    private String opinion;
    List<String> opinionFileIds;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Date getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Date meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getExperts() {
        return experts;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public List<String> getOpinionFileIds() {
        return opinionFileIds;
    }

    public void setOpinionFileIds(List<String> opinionFileIds) {
        this.opinionFileIds = opinionFileIds;
    }
}
