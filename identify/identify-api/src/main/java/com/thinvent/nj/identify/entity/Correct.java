/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : Correct.java
 * 功能概要       :
 * 做成日期       : 2018-12-05・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;
import java.util.List;

/**
* 补正业务表
* @author panqh
* @date 2018-12-05
*/
public class Correct extends BaseOracleEntity {

    // 鉴定主表ID
    private String mainId;

    // 补正业务状态
    private Integer status;
    // 补正申请时间
    private Date requestTime;


    // 鉴定报告明细ids
    private String reportDetailIds;

    // 补正申请原因
    private String reason;

    // 流程状态(0: 进行中；1:已挂起；2:已终止)
    private Integer suspend;
    // 操作时间
    private Date operatorTime;


    /**
     * @author : xujc
     * @date :2018/12/17
     * @Description :以下是补充字段(非本表字段)
     */

    // 补正文件id集合
    private List<String> correctFileIds;

    private String caseNo;
    private String zone;
    private String street;
    private String address;
    private String orgName;

    private Integer method;
    // 直接懒加载
    private IdentifyMain identifyMain;
    // service 层赋值
    private IdentifyReport identifyReport;
    // 直接懒加载
    private CorrectUpload correctUpload;

    public CorrectUpload getCorrectUpload() {
        return correctUpload;
    }

    public void setCorrectUpload(CorrectUpload correctUpload) {
        this.correctUpload = correctUpload;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getSuspend() {
        return suspend;
    }

    public void setSuspend(Integer suspend) {
        this.suspend = suspend;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public List<String> getCorrectFileIds() {
        return correctFileIds;
    }

    public void setCorrectFileIds(List<String> correctFileIds) {
        this.correctFileIds = correctFileIds;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
    public Integer getMethod() {
        return method;
    }
    public void setMethod(Integer method) {
        this.method = method;
    }
    public IdentifyReport getIdentifyReport() {
        return identifyReport;
    }
    public void setIdentifyReport(IdentifyReport identifyReport) {
        this.identifyReport = identifyReport;
    }
    public IdentifyMain getIdentifyMain() {
        return identifyMain;
    }

    public void setIdentifyMain(IdentifyMain identifyMain) {
        this.identifyMain = identifyMain;
    }

    public String getReportDetailIds() {
        return reportDetailIds;
    }

    public void setReportDetailIds(String reportDetailIds) {
        this.reportDetailIds = reportDetailIds;
    }
}
