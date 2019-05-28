package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;
import java.util.List;

/**
* 鉴定机构名录库主表
* @author wangwj
* @date 2019-3-4
*/
  //add by wangwj 20190304 start
public class IdentifyOrgMain extends BaseOracleEntity {

    // 单位名称
    private String name;

    // 单位住所
    private String address;

    // 注册资本
    private String registeredCapital;

    // 成立日期
    private Date buildDate;

    // 营业期限-开始
    private Date businessTermStart;

    // 营业期限-结束
    private Date businessTermEnd;

    // 统一社会信用代码
    private String unifiedCode;

    // 营业执照登记机关
    private String registrationOffice;

    // 检测检验机构资质认证书编号
    private String orgCode;

    // 检测检验机构资质有效日期-开始
    private Date orgStart;

    // 检测检验机构资质有效日期-结束
    private Date orgEnd;

    // 建设工程质量检测机构资质证书编号
    private String qualiCode;

    // 建设工程质量检测机构资质核定项目
    private String qualiProject;

    // 建设工程质量检测机构资质核定项目有效日期-开始
    private Date qualiProjectStart;

    // 建设工程质量检测机构资质核定项目有效日期-结束
    private Date qualiProjectEnd;

    // 测绘资质证书编号
    private String mappingCode;

    // 测绘资质证书有效日期-开始
    private Date mappingStart;

    // 测绘资质证书有效日期-结束
    private Date mappingEnd;

    // 鉴定人数总数
    private Integer totalNum;

    // 高级工程师人数
    private Integer advPersonNum;

    // 中级工程师人数
    private Integer midPersonNum;

    // 一级注册结构工程师人数
    private Integer level1Num;

    // 二级注册结构工程师人数
    private Integer level2Num;

    // 注册岩土工程师人数
    private Integer rockNum;

    // 申请类型（1：初始；2：变更；3：取消）
    private String requestType;

    // 有无处分和责任事故
    private String punishment;

    // 行政审批平台ORG_ID
    private String platformId;

    // 单位类型
    private String type;

    //启用状态
    private Integer useStatus;
    // 法定代表人
    private String personName;

    //法定代表人&技术负责人
    List<IdentifyOrgPerson> identifyOrgPersonList;

    //鉴定人员信息表
    List<IdentifyOrgIdentify> identifyOrgIdentifyList;

    //使用设备信息表
    List<IdentifyOrgDevice> identifyOrgDeviceList;

    //鉴定机构业务类型关系表
    List<IdentifyOrgType> identifyOrgTypeList;

    // 冗余字段
    private String creditGradeName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Date getBusinessTermStart() {
        return businessTermStart;
    }

    public void setBusinessTermStart(Date businessTermStart) {
        this.businessTermStart = businessTermStart;
    }

    public Date getBusinessTermEnd() {
        return businessTermEnd;
    }

    public void setBusinessTermEnd(Date businessTermEnd) {
        this.businessTermEnd = businessTermEnd;
    }

    public String getUnifiedCode() {
        return unifiedCode;
    }

    public void setUnifiedCode(String unifiedCode) {
        this.unifiedCode = unifiedCode;
    }

    public String getRegistrationOffice() {
        return registrationOffice;
    }

    public void setRegistrationOffice(String registrationOffice) {
        this.registrationOffice = registrationOffice;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Date getOrgStart() {
        return orgStart;
    }

    public void setOrgStart(Date orgStart) {
        this.orgStart = orgStart;
    }

    public Date getOrgEnd() {
        return orgEnd;
    }

    public void setOrgEnd(Date orgEnd) {
        this.orgEnd = orgEnd;
    }

    public String getQualiCode() {
        return qualiCode;
    }

    public void setQualiCode(String qualiCode) {
        this.qualiCode = qualiCode;
    }

    public String getQualiProject() {
        return qualiProject;
    }

    public void setQualiProject(String qualiProject) {
        this.qualiProject = qualiProject;
    }

    public Date getQualiProjectStart() {
        return qualiProjectStart;
    }

    public void setQualiProjectStart(Date qualiProjectStart) {
        this.qualiProjectStart = qualiProjectStart;
    }

    public Date getQualiProjectEnd() {
        return qualiProjectEnd;
    }

    public void setQualiProjectEnd(Date qualiProjectEnd) {
        this.qualiProjectEnd = qualiProjectEnd;
    }

    public String getMappingCode() {
        return mappingCode;
    }

    public void setMappingCode(String mappingCode) {
        this.mappingCode = mappingCode;
    }

    public Date getMappingStart() {
        return mappingStart;
    }

    public void setMappingStart(Date mappingStart) {
        this.mappingStart = mappingStart;
    }

    public Date getMappingEnd() {
        return mappingEnd;
    }

    public void setMappingEnd(Date mappingEnd) {
        this.mappingEnd = mappingEnd;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getAdvPersonNum() {
        return advPersonNum;
    }

    public void setAdvPersonNum(Integer advPersonNum) {
        this.advPersonNum = advPersonNum;
    }

    public Integer getMidPersonNum() {
        return midPersonNum;
    }

    public void setMidPersonNum(Integer midPersonNum) {
        this.midPersonNum = midPersonNum;
    }

    public Integer getLevel1Num() {
        return level1Num;
    }

    public void setLevel1Num(Integer level1Num) {
        this.level1Num = level1Num;
    }

    public Integer getLevel2Num() {
        return level2Num;
    }

    public void setLevel2Num(Integer level2Num) {
        this.level2Num = level2Num;
    }

    public Integer getRockNum() {
        return rockNum;
    }

    public void setRockNum(Integer rockNum) {
        this.rockNum = rockNum;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public List<IdentifyOrgPerson> getIdentifyOrgPersonList() {
        return identifyOrgPersonList;
    }

    public void setIdentifyOrgPersonList(List<IdentifyOrgPerson> identifyOrgPersonList) {
        this.identifyOrgPersonList = identifyOrgPersonList;
    }

    public List<IdentifyOrgIdentify> getIdentifyOrgIdentifyList() {
        return identifyOrgIdentifyList;
    }

    public void setIdentifyOrgIdentifyList(List<IdentifyOrgIdentify> identifyOrgIdentifyList) {
        this.identifyOrgIdentifyList = identifyOrgIdentifyList;
    }

    public List<IdentifyOrgDevice> getIdentifyOrgDeviceList() {
        return identifyOrgDeviceList;
    }

    public void setIdentifyOrgDeviceList(List<IdentifyOrgDevice> identifyOrgDeviceList) {
        this.identifyOrgDeviceList = identifyOrgDeviceList;
    }

    public List<IdentifyOrgType> getIdentifyOrgTypeList() {
        return identifyOrgTypeList;
    }

    public void setIdentifyOrgTypeList(List<IdentifyOrgType> identifyOrgTypeList) {
        this.identifyOrgTypeList = identifyOrgTypeList;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCreditGradeName() {
        return creditGradeName;
    }

    public void setCreditGradeName(String creditGradeName) {
        this.creditGradeName = creditGradeName;
    }
}


//add by wangwj 20190304 end