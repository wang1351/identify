package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* 企业信用表
* @author administrator
* @date 2018-11-12
*/
public class OrgCredit extends BaseOracleEntity {

    // 评分年度
    private Date scoreYear;

    // 鉴定机构id
    private String identifyOrgId;

    // 鉴定机构名称
    private String orgName;

    // 统一社会信用代码
    private String unifiedCode;

    // 法定代表人
    private String legalPerson;

    // 信用级别得分
    private Integer creditGradeScore;

    // 信用级别名称
    private String creditGradeName;

    //add by wangwj 20181226
    //状态
    private Integer status;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //add by wangwj 20181226 end

    public Date getScoreYear() {
        return scoreYear;
    }

    public void setScoreYear(Date scoreYear) {
        this.scoreYear = scoreYear;
    }

    public String getIdentifyOrgId() {
        return identifyOrgId;
    }

    public void setIdentifyOrgId(String identifyOrgId) {
        this.identifyOrgId = identifyOrgId;
    }

    public Integer getCreditGradeScore() {
        return creditGradeScore;
    }

    public void setCreditGradeScore(Integer creditGradeScore) {
        this.creditGradeScore = creditGradeScore;
    }

    public String getCreditGradeName() {
        return creditGradeName;
    }

    public void setCreditGradeName(String creditGradeName) {
        this.creditGradeName = creditGradeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUnifiedCode() {
        return unifiedCode;
    }

    public void setUnifiedCode(String unifiedCode) {
        this.unifiedCode = unifiedCode;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }
}
