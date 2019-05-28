package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* T_IDENTIFY_ACCEPT
* @author administrator
* @date 2018-11-21
*/
public class IdentifyAccept extends BaseOracleEntity {

    // 受理意见
    private Integer accept;

    // 房屋安全鉴定委托书
    private String fileId;

    // 分派负责人ID
    private String chargeId;

    // 负责人名称
    private String userName;

    // 主表ID
    private String mainId;

    // 要求完成时间
    private Date requireDate;

    // 紧急度
    private Integer emergency;

    // 不受理原因
    private String unacceptReason;

    // 参与人员ID
    private String joinPerson;

    // 参与人员名称
    private String joinPersonName;


    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public Date getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(Date requireDate) {
        this.requireDate = requireDate;
    }

    public Integer getEmergency() {
        return emergency;
    }

    public void setEmergency(Integer emergency) {
        this.emergency = emergency;
    }

    public String getUnacceptReason() {
        return unacceptReason;
    }

    public void setUnacceptReason(String unacceptReason) {
        this.unacceptReason = unacceptReason;
    }

    public String getJoinPerson() {
        return joinPerson;
    }

    public void setJoinPerson(String joinPerson) {
        this.joinPerson = joinPerson;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJoinPersonName() {
        return joinPersonName;
    }

    public void setJoinPersonName(String joinPersonName) {
        this.joinPersonName = joinPersonName;
    }
}
