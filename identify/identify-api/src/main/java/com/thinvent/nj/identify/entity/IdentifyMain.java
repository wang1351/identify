package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* T_IDENTIFY_MAIN
* @author administrator
* @date 2018-11-13
*/
public class IdentifyMain extends BaseOracleEntity {

    // 编号
    private String caseNo;

    // 鉴定单位
    private String orgId;
    // 鉴定机构
    private String orgName;

    // 鉴定业务当前状态
    private Integer status;

    // 流程状态(0: 进行中；1:已挂起；2:已终止)
    private Integer suspend;

    // 鉴定申请方式
    private Integer method;

    // 申请时间
    private Date requestTime;

    // 鉴定类别(存字典表value)
    private String content;

    // 鉴定内容-评级层次(存字典表value)
    private String ratingLevel;

    // 鉴定内容-评级层次-评级类别(存字典表value)
    private String ratingType;
    // 冗余
    private String ratingLevelName;
    private String ratingTypeName;

    // 是否复审(0: 否；1:是)
    private Integer isReview;

    // 是否补正(0: 否；1:是)
    private Integer isCorrect;

    // 鉴定内容其他时，手填
    private String otherContent;

    // 操作时间
    private Date operatorTime;

    private String zone;

    private String street;

    //房屋地址
    private String address;

    // 委托人信息
    private Client client;

    // 房屋基本信息
    private House house;

    // 新建工程
    private NewProject newProject;

    //鉴定内容
    private String jd;

    // 未鉴定次数
    private Integer unfinishedCount;


    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
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

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSuspend() {
        return suspend;
    }

    public void setSuspend(Integer suspend) {
        this.suspend = suspend;
    }



    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOtherContent() {
        return otherContent;
    }

    public void setOtherContent(String otherContent) {
        this.otherContent = otherContent;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public NewProject getNewProject() {
        return newProject;
    }

    public void setNewProject(NewProject newProject) {
        this.newProject = newProject;
    }

    public String getRatingLevel() {
        return ratingLevel;
    }

    public void setRatingLevel(String ratingLevel) {
        this.ratingLevel = ratingLevel;
    }

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Integer getUnfinishedCount() {
        return unfinishedCount;
    }

    public void setUnfinishedCount(Integer unfinishedCount) {
        this.unfinishedCount = unfinishedCount;
    }

    public String getRatingLevelName() {
        return ratingLevelName;
    }

    public void setRatingLevelName(String ratingLevelName) {
        this.ratingLevelName = ratingLevelName;
    }

    public String getRatingTypeName() {
        return ratingTypeName;
    }

    public void setRatingTypeName(String ratingTypeName) {
        this.ratingTypeName = ratingTypeName;
    }
}
