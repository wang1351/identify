package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;
import java.util.List;

/**
* 法定代表人&技术负责人表
* @author wangwj
* @date 2019-3-4
*/

//add by wangwj 20190304 start

public class IdentifyOrgPerson extends BaseOracleEntity {

    // 出生年月
    private Date birthday;

    // 法定代表人or技术负责人（1：法定代表人；2：技术负责人）
    private String type;

    // 学历
    private String education;

    // 技术职称等级
    private String titleDegree;

    // 职称证书编号
    private String certificateNo;

    // 执业资格
    private String practise;

    // 注册证书编号
    private String regCertificateNo;

    // 鉴定机构ID
    private String orgId;

    // 姓名
    private String name;

    // 性别（1：男；2：女）
    private String sex;

    // 何时/何校/何专业毕业
    private String graduation;

    // 鉴定相关工作年限
    private String workYear;

    // 办公电话
    private String officePhone;

    // 移动电话
    private String phone;

    // 有无处分和责任事故
    private String punishment;

    // 身份证号
    private String identityNo;

    //工作简历表
    List<IdentifyOrgResume> identifyOrgResumeList;


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTitleDegree() {
        return titleDegree;
    }

    public void setTitleDegree(String titleDegree) {
        this.titleDegree = titleDegree;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getPractise() {
        return practise;
    }

    public void setPractise(String practise) {
        this.practise = practise;
    }

    public String getRegCertificateNo() {
        return regCertificateNo;
    }

    public void setRegCertificateNo(String regCertificateNo) {
        this.regCertificateNo = regCertificateNo;
    }


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public List<IdentifyOrgResume> getIdentifyOrgResumeList() {
        return identifyOrgResumeList;
    }

    public void setIdentifyOrgResumeList(List<IdentifyOrgResume> identifyOrgResumeList) {
        this.identifyOrgResumeList = identifyOrgResumeList;
    }
}

//add by wangwj 20190304 end