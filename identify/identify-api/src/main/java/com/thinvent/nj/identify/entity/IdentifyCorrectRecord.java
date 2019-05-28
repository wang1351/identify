package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
 * @author : xujc
 * @date :2018/12/14
 * @Description : 补正流程记录表的实体
 */
public class IdentifyCorrectRecord extends BaseOracleEntity {
    // 复审业务主表ID
    private String correctId;

    private String operatorUserName;



    private Integer status;

    private String businessKey;

    // 排序号
    private Integer sort;

    // 说明
    private String remarks;
    // 操作时间
    private Date operatorTime;


    public String getCorrectId() {
        return correctId;
    }

    public void setCorrectId(String correctId) {
        this.correctId = correctId;
    }

    public String getOperatorUserName() {
        return operatorUserName;
    }

    public void setOperatorUserName(String operatorUserName) {
        this.operatorUserName = operatorUserName;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
