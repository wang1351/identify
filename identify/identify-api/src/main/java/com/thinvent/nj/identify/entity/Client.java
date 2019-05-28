/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : Client.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.List;

/**
 * 委托人信息实体
 * @author panqh
 * @date 2018-11-13
 */
public class Client extends BaseOracleEntity {

    /**
     * 鉴定业务主表ID
     */
    private String mainId;

    /**
     * 委托人姓名
     */
    private String clientName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * add by wangwj 20181213 start
     * 联系电话2
     */
    private String phone2;

    /**
     * 委托人性质（1: 产权人； 2: 使用人； 3: 第三方）
     */
    private Integer nature;

    /**
     * 鉴定委托的目的及内容
     */
    private String content;

    /**
     * 房屋使用变迁，改建，维修状况
     */
    private String condition;

    /**
     * 委托人附件List
     */
    private List<ClientFile> clientFileList;

    /**
     * 身份证号
     * @return
     */
    private String idNum;

    /**
     * 房屋产权证号
     * @return
     */
    private String propertyNum;

    /**
     * 鉴定委托书
     */
    private String clientIdentifyFileId;


    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPropertyNum() {
        return propertyNum;
    }

    public void setPropertyNum(String propertyNum) {
        this.propertyNum = propertyNum;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<ClientFile> getClientFileList() {
        return clientFileList;
    }

    public void setClientFileList(List<ClientFile> clientFileList) {
        this.clientFileList = clientFileList;
    }

    public String getClientIdentifyFileId() {
        return clientIdentifyFileId;
    }

    public void setClientIdentifyFileId(String clientIdentifyFileId) {
        this.clientIdentifyFileId = clientIdentifyFileId;
    }
}
