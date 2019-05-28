/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : Review.java
 * 功能概要       :
 * 做成日期       : 2018-11-27・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;
import java.util.List;

/**
* 复核业务实体
* @author panqh
* @date 2018-11-27
*/
public class Review extends BaseOracleEntity {

    // 房屋鉴定业务主表ID
    private String mainId;
    // 鉴定报告明细ids
    private String reportDetailIds;
    //申请人
    private String requestName;
    // 身份证号
    private String idNum;
    // 联系人
    private String contactName;
    // 联系电话
    private String phone;
    // 联系地址
    private String contactAddress;
    // 申请表id
    private String sheetId;

    // 操作时间
    private Date operatorTime;
    // 复核申请时间
    private Date requestTime;

    // 复核原因
    private String reason;

    // 复核业务状态（1:：已申请，2：已受理，3：已选择专家，4：已确认专家 5：已填写意见 6 已发放）
    private Integer status;

    // 编号
    private String caseNo;

    // 行政区划
    private String zone;

    // 街道
    private String street;

    // 地址
    private String address;

    // 鉴定机构
    private String orgName;

    // 申请方式
    private String method;



    // 冗余
    private Integer sort;
    // 鉴定报告文件id
    private String identifyFileId;
    // 指定专家集合（直接懒加载）
    private List<ExpertAppoint> expertAppoints;
    // 回避专家ids
    private String exceptExpertIds;
    // 回避专家集合(service层赋值)
    private List<Expert> experts;
    // 流程状态(0: 进行中；1:已挂起；2:已终止)
    private Integer suspend;
    // 直接懒加载
    private IdentifyMain identifyMain;
    // service 层赋值
    private IdentifyReport identifyReport;

    public IdentifyMain getIdentifyMain() {
        return identifyMain;
    }

    public void setIdentifyMain(IdentifyMain identifyMain) {
        this.identifyMain = identifyMain;
    }

    public IdentifyReport getIdentifyReport() {
        return identifyReport;
    }

    public void setIdentifyReport(IdentifyReport identifyReport) {
        this.identifyReport = identifyReport;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getIdentifyFileId() {
        return identifyFileId;
    }

    public void setIdentifyFileId(String identifyFileId) {
        this.identifyFileId = identifyFileId;
    }

    public List<ExpertAppoint> getExpertAppoints() {
        return expertAppoints;
    }

    public void setExpertAppoints(List<ExpertAppoint> expertAppoints) {
        this.expertAppoints = expertAppoints;
    }



    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getExceptExpertIds() {
        return exceptExpertIds;
    }

    public void setExceptExpertIds(String exceptExpertIds) {
        this.exceptExpertIds = exceptExpertIds;
    }

    public Integer getSuspend() {
        return suspend;
    }

    public void setSuspend(Integer suspend) {
        this.suspend = suspend;
    }

    public String getReportDetailIds() {
        return reportDetailIds;
    }

    public void setReportDetailIds(String reportDetailIds) {
        this.reportDetailIds = reportDetailIds;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public List<Expert> getExperts() {
        return experts;
    }

    public void setExperts(List<Expert> experts) {
        this.experts = experts;
    }
}
