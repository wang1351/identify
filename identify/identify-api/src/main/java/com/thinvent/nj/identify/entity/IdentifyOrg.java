package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* 鉴定机构名录库
* @author panqh
* @date 2018-11-01
*/
public class IdentifyOrg extends BaseOracleEntity {

    /**
     * 鉴定机构名称
     */
    private String name;

    /**
     * 统一社会信用代码
     */
    private String unifiedCode;

    /**
     * 法定代表人
     */
    private String legalPerson;

    /**
     * 同步至UC状态（0:未同步；1:已同步）
     */
    private Integer syncToStatus;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * 入库时间
     */
    private Date inDate;

    /**
     * 启用状态：（0:禁用；1:启用）
     */
    private Integer useStatus;

    /**
     * 同步至UC时间
     */
    private Date syncToDate;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 同步UC组织机构的组织编码
     */
    private String syncOrgCode;

    /**
     * 鉴定业务KEY（"," 分割）
     */
    private String identifyBusinessKey;

    /**
     * 鉴定业务名称（“，”分割）
     */
    private String identifyBusinessName;





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getSyncToStatus() {
        return syncToStatus;
    }

    public void setSyncToStatus(Integer syncToStatus) {
        this.syncToStatus = syncToStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Date getSyncToDate() {
        return syncToDate;
    }

    public void setSyncToDate(Date syncToDate) {
        this.syncToDate = syncToDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSyncOrgCode() {
        return syncOrgCode;
    }

    public void setSyncOrgCode(String syncOrgCode) {
        this.syncOrgCode = syncOrgCode;
    }

    public String getIdentifyBusinessKey() {
        return identifyBusinessKey;
    }

    public void setIdentifyBusinessKey(String identifyBusinessKey) {
        this.identifyBusinessKey = identifyBusinessKey;
    }

    public String getIdentifyBusinessName() {
        return identifyBusinessName;
    }

    public void setIdentifyBusinessName(String identifyBusinessName) {
        this.identifyBusinessName = identifyBusinessName;
    }

}
