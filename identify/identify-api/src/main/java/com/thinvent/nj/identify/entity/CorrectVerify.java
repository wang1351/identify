package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * @author : xujc
 * @date :2018/12/17
 * @Description : 补正审核表
 */
public class CorrectVerify extends BaseOracleEntity {
    // 补正业务表ID
    private String correctId;
    //审核结果（2:不通过；1：通过）
    private Integer checkResult;
    // 审核意见
    private String checkOpinion;

    public String getCorrectId() {
        return correctId;
    }

    public void setCorrectId(String correctId) {
        this.correctId = correctId;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }
}
