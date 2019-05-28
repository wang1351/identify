package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;
import java.util.List;

/**
* T_IDENTIFY_REPORT
* @author wangwj
* @date 2018-11-21
*/
public class IdentifyReport extends BaseOracleEntity {

    // 房屋鉴定业务主表ID
    private String mainId;

    // 上传方式（1: 分栋上传；2:汇总上传）
    private Integer method;

    // 鉴定报告文件ID
    private String identifyFileId;

    // 检测报告文件ID
    private String testingFileId;

    // 是否是激活鉴定（0：否；1： 是）
    private Integer activeIdentify;

    // 冗余
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


    //鉴定报告审核表
    private IdentifyVerify identifyVerify;

    // 鉴定报告详细表集合
    private List<IdentifyReportDetail> reportDetailList;



    public IdentifyVerify getIdentifyVerify() {
        return identifyVerify;
    }

    public void setIdentifyVerify(IdentifyVerify identifyVerify) {
        this.identifyVerify = identifyVerify;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

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

    public List<IdentifyReportDetail> getReportDetailList() {
        return reportDetailList;
    }

    public void setReportDetailList(List<IdentifyReportDetail> reportDetailList) {
        this.reportDetailList = reportDetailList;
    }

    public Integer getActiveIdentify() {
        return activeIdentify;
    }

    public void setActiveIdentify(Integer activeIdentify) {
        this.activeIdentify = activeIdentify;
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
