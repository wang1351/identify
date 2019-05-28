package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* 鉴定报告明细
* @author liupj
* @date 2018-12-17
*/
public class IdentifyReportDetail extends BaseOracleEntity {

    // 鉴定报告ID
    private String reportId;

    // 房屋分栋ID
    private String houseSplitId;

    // 房屋名称
    private String houseName;

    // 鉴定等级（存字典Value）
    private String identifyResult;

    // 鉴定等级(名称)
    private String identifyResultName;

    // 鉴定结论
    private String conclusion;

    // 处理意见
    private String opinion;

    // 鉴定报告文件ID
    private String identifyFileId;

    // 检测报告文件ID
    private String testingFileId;

    // 序号
    private Integer sort;

    // 项目名称
    private String proName;

    // 平台编号
    private String caseNo;

    // 鉴定类别
    private String identifyType;

    // 报告日期
    private Date reportDate;

    // 委托人姓名
    private String clientName;

    // 鉴定单位名称
    private String identifyOrgName;

    // 二维码附件ID
    private String qrFileId;


    public String getIdentifyFileId() {
        return identifyFileId;
    }

    public void setIdentifyFileId(String identifyFileId) {
        this.identifyFileId = identifyFileId;
    }

    public String getTestingFileId() {
        return testingFileId;
    }

    public void setTestingFileId(String testingFileId) {
        this.testingFileId = testingFileId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getIdentifyResult() {
        return identifyResult;
    }

    public void setIdentifyResult(String identifyResult) {
        this.identifyResult = identifyResult;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getHouseSplitId() {
        return houseSplitId;
    }

    public void setHouseSplitId(String houseSplitId) {
        this.houseSplitId = houseSplitId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getIdentifyResultName() {
        return identifyResultName;
    }

    public void setIdentifyResultName(String identifyResultName) {
        this.identifyResultName = identifyResultName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIdentifyOrgName() {
        return identifyOrgName;
    }

    public void setIdentifyOrgName(String identifyOrgName) {
        this.identifyOrgName = identifyOrgName;
    }

    public String getQrFileId() {
        return qrFileId;
    }

    public void setQrFileId(String qrFileId) {
        this.qrFileId = qrFileId;
    }
}
