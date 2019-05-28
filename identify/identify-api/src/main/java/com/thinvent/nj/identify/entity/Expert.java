package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* 鉴定专家
* @author xujc
* @date 2018-10-31
*/
public class Expert extends BaseOracleEntity {

    // 入库时间
    private Date inDate;

    // 身份证号
    private String identifiedCode;

    // 民族
    private String nationName;

    // 性别
    private String sexName;

    // 学历
    private String educationName;

    // 学位
    private String degreeName;

    // 健康状况
    private String healthName;

    // 出生日期
    private Date birthday;



    // 所在单位名称
    private String companyName;

    // 执业证号
    private String practiceNum;

    // 手机号码
    private String phone;

    // e-mail
    private String email;

    // 本人照片ID（FK -> T_BASE_FILE.ID)
    private String photo;

    // 申请表ID(FK -> T_BASE_FILE.ID)
    private String applyId;

    // 姓名
    private String name;

    // 启用/禁用 状态(0:禁用 1：启用)
    private String useStatusName;
    // 专家下属的业务领域对象集合
    private List<ExpertBusinessArea> list = new ArrayList<>();

    // 民族(T_BASE_DICT_ITEM.value: 待定)
    private Integer nation;

    // 性别(T_BASE_DICT_ITEM.id)
    private Integer sex;

    // 学历(T_BASE_DICT_ITEM.id)
    private Integer education;

    // 学位(T_BASE_DICT_ITEM.id)
    private Integer degree;

    // 健康状况(T_BASE_DICT_ITEM.value: 待定)
    private Integer health;

    //update by wangwj 20181220 start
    // 行业执业资格
    private String practice;

    // 职称
    private String title;
    //update by wangwj 20181220 end

    // 启用/禁用 状态(0:禁用 1：启用)
    private Integer useStatus;

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getIdentifiedCode() {
        return identifiedCode;
    }

    public void setIdentifiedCode(String identifiedCode) {
        this.identifiedCode = identifiedCode;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getHealthName() {
        return healthName;
    }

    public void setHealthName(String healthName) {
        this.healthName = healthName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPracticeNum() {
        return practiceNum;
    }

    public void setPracticeNum(String practiceNum) {
        this.practiceNum = practiceNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseStatusName() {
        return useStatusName;
    }

    public void setUseStatusName(String useStatusName) {
        this.useStatusName = useStatusName;
    }

    public List<ExpertBusinessArea> getList() {
        return list;
    }

    public void setList(List<ExpertBusinessArea> list) {
        this.list = list;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    //update by wangwj 20181220 start
    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //update by wangwj 20181220 end

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
}
