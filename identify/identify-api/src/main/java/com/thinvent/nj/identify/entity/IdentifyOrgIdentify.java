package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* 鉴定人员信息表
* @author wangwj
* @date 2019-3-4
*/
//add by wangwj 20190304 start
public class IdentifyOrgIdentify extends BaseOracleEntity {

    // 鉴定机构ID
    private String orgId;

    // 姓名
    private String name;

    // 身份证号码
    private String identityNo;

    // 序号
    private Integer seq;

    // 学历
    private String education;

    // 职称
    private String job;

    // 执业资格
    private String practise;

    // 鉴定相关工作年限
    private String workYear;

    // 专业
    private String major;



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

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPractise() {
        return practise;
    }

    public void setPractise(String practise) {
        this.practise = practise;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
  //add by wangwj 20190304 end