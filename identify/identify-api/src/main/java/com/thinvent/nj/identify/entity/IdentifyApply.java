package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
 * @author : xujc
 * @date :2019/4/9
 * @Description : 手机端鉴定实体
 */
public class IdentifyApply extends BaseOracleEntity {
    // 鉴定类别(类型id)
    private String identifyType;
    // 鉴定单位
    private String identifyOrgId;
    // 鉴定机构
    private String orgName;
    // 委托人
    private String identifyClientete;
    //房屋地址
    private String identifyHouseAddress;

    /**
     * 联系人姓名
     */
    private String identifyContacts;

    /**
     * 联系电话
     */
    private String identifyContactsTel;

    // 鉴定目的
    private String identifyObjective;

    // 鉴定业务当前状态（是否已发起正式申请，0 未发起; 1已发起,2 已拒绝）
    private Integer identifyStatue;

    // 来源（1：我的南京；2：房产政务）
    private Integer identifySource;

       // 编号
    private String identifyCode;

    // 鉴定内容为其他时的内容
    private String otherContent;
    // 字典项的类型value
    private  String typeValue;
    // 鉴定类型名称
    private String typeName;

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }

    public String getIdentifyOrgId() {
        return identifyOrgId;
    }

    public void setIdentifyOrgId(String identifyOrgId) {
        this.identifyOrgId = identifyOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getIdentifyClientete() {
        return identifyClientete;
    }

    public void setIdentifyClientete(String identifyClientete) {
        this.identifyClientete = identifyClientete;
    }

    public String getIdentifyHouseAddress() {
        return identifyHouseAddress;
    }

    public void setIdentifyHouseAddress(String identifyHouseAddress) {
        this.identifyHouseAddress = identifyHouseAddress;
    }

    public String getIdentifyContacts() {
        return identifyContacts;
    }

    public void setIdentifyContacts(String identifyContacts) {
        this.identifyContacts = identifyContacts;
    }

    public String getIdentifyContactsTel() {
        return identifyContactsTel;
    }

    public void setIdentifyContactsTel(String identifyContactsTel) {
        this.identifyContactsTel = identifyContactsTel;
    }

    public String getIdentifyObjective() {
        return identifyObjective;
    }

    public void setIdentifyObjective(String identifyObjective) {
        this.identifyObjective = identifyObjective;
    }

    public Integer getIdentifyStatue() {
        return identifyStatue;
    }

    public void setIdentifyStatue(Integer identifyStatue) {
        this.identifyStatue = identifyStatue;
    }

    public Integer getIdentifySource() {
        return identifySource;
    }

    public void setIdentifySource(Integer identifySource) {
        this.identifySource = identifySource;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getOtherContent() {
        return otherContent;
    }

    public void setOtherContent(String otherContent) {
        this.otherContent = otherContent;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
