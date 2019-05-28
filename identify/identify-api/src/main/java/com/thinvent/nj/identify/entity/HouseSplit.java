/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : HouseSplit.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.List;

/**
 * 房屋分栋信息实体
 * @author panqh
 * @date 2018-11-13
 */
public class HouseSplit extends BaseOracleEntity {

    /**
     * 房屋基本信息表ID
     */
    private String houseId;

    /**
     * 房屋名称
     */
    private String houseName;

    /**
     * 结构类型（FK -> 字典项Value)
     */
    private String structure;

    //add by wangwj 20181221 start
    /**
     * 房屋层数(地上)
     */
     private Integer layerAbove;

    /**
     * 房屋层数(地下)
     */
     private Integer layerUnder;
    //add by wangwj 20181221 end
    /**
     * 建筑年代
     */
    private String buildYear;

    /**
     * 设计用途
     */
    private String purpose;

    /**
     * 建筑面积
     */
    private Double area;

    /**
     * 鉴定面积
     */
    private Double identifyArea;

    /**
     * 鉴定部位
     */
    private String position;

    /**
     * 权属性质
     */
    private String nature;

    /**
     * 产权人
     */
    private String holderPerson;

    /**
     * 使用人
     */
    private String person;

    /**
     * 施工单位
     */
    private String constructOrg;

    /**
     * 设计单位
     */
    private String designOrg;

    /**
     * 排序号
     */
    private Integer sort;

    //add by wangwj 20181213 start
    /**
     * 结构类型为其他时，手填项
     */
    private String otherContent;

    /**
     * 勘查单位
     */
    private String prospectOrg;

    /**
     * 监理单位
     */
    private String supervisionOrg;
    //add by wangwj 20181213 end

    /**
     * 结构类型
     */
    private String jg;

    /**
     * 分栋附件列表
     */
    private List<HouseSplitFile> houseSplitFileList;


    public String getOtherContent() {
        return otherContent;
    }

    public void setOtherContent(String otherContent) {
        this.otherContent = otherContent;
    }

    public String getProspectOrg() {
        return prospectOrg;
    }

    public void setProspectOrg(String prospectOrg) {
        this.prospectOrg = prospectOrg;
    }

    public String getSupervisionOrg() {
        return supervisionOrg;
    }

    public void setSupervisionOrg(String supervisionOrg) {
        this.supervisionOrg = supervisionOrg;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getLayerAbove() {
        return layerAbove;
    }

    public void setLayerAbove(Integer layerAbove) {
        this.layerAbove = layerAbove;
    }

    public Integer getLayerUnder() {
        return layerUnder;
    }

    public void setLayerUnder(Integer layerUnder) {
        this.layerUnder = layerUnder;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getIdentifyArea() {
        return identifyArea;
    }

    public void setIdentifyArea(Double identifyArea) {
        this.identifyArea = identifyArea;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getHolderPerson() {
        return holderPerson;
    }

    public void setHolderPerson(String holderPerson) {
        this.holderPerson = holderPerson;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getConstructOrg() {
        return constructOrg;
    }

    public void setConstructOrg(String constructOrg) {
        this.constructOrg = constructOrg;
    }

    public String getDesignOrg() {
        return designOrg;
    }

    public void setDesignOrg(String designOrg) {
        this.designOrg = designOrg;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<HouseSplitFile> getHouseSplitFileList() {
        return houseSplitFileList;
    }

    public void setHouseSplitFileList(List<HouseSplitFile> houseSplitFileList) {
        this.houseSplitFileList = houseSplitFileList;
    }
}
