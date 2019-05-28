/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : NewProject.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;
import java.util.List;

/**
* 房屋新建工程实体
* @author panqh
* @date 2018-11-13
*/
public class NewProject extends BaseOracleEntity {

    // 止水方案
    private String stopMode;

    // 工程名称
    private String projectName;

    // 总建筑面积
    private Double totalArea;

    // 建设单位
    private String buildOrg;

    // 地上层
    private Integer layerGround;

    // 地下层
    private Integer layerUnderGround;

    // 设计单位
    private String designOrg;

    // 施工单位
    private String constructionOrg;

    // 结构
    private String structure;

    // 监理单位
    private String supervisionOrg;

    //add by wangwj 20181213 start
    //勘查单位
    private String prospectedOrg;
    //add by wangwj 20181213 end

    // 用途
    private String purpose;

    // 工程桩施工单位
    private String pileConstructionOrg;

    // 工程桩桩型
    private String pileModel;

    // 工程桩设计单位
    private String pileDesignOrg;

    // 工程桩桩长
    private Double pileLength;

    // 工程桩开工时间
    private Date pileStartDate;

    // 工程桩直径
    private Double pileDiameter;

    // 工程桩竣工时间
    private Date pileEndDate;

    // 支护桩设计单位
    private String supportDesignOrg;

    // 支护桩桩型
    private String supportModel;

    // 支护桩施工单位
    private String supportConstructionOrg;

    // 支护桩长
    private Double supportLength;

    // 支护桩开工时间
    private Date supportStartDate;

    // 支护桩直径
    private Double supportDiameter;

    // 支护桩竣工时间
    private Date supportEndDate;

    // 支撑结构
    private String supStructure;

    // 间距
    private Double spacing;

    // 道路沉降量
    private String roadSettlement;

    // 桩位移量
    private String pileDisplacement;

    // 建筑物沉降量
    private String buildingSettlement;

    // 基坑面积
    private Double foundationArea;

    // 开挖深度
    private Double depth;

    // 距邻房
    private Double margin;

    // 开挖时间
    private Date workDate;

    // 竣工时间
    private Date endDate;

    // 排水方式
    private String drainageMode;

    // 房屋基本信息表ID
    private String mainId;

    // 新建工程附件
    private List<NewProFile> newProFileList;


    public String getProspectedOrg() {
        return prospectedOrg;
    }

    public void setProspectedOrg(String prospectedOrg) {
        this.prospectedOrg = prospectedOrg;
    }

    public String getStopMode() {
        return stopMode;
    }

    public void setStopMode(String stopMode) {
        this.stopMode = stopMode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public String getBuildOrg() {
        return buildOrg;
    }

    public void setBuildOrg(String buildOrg) {
        this.buildOrg = buildOrg;
    }

    public Integer getLayerGround() {
        return layerGround;
    }

    public void setLayerGround(Integer layerGround) {
        this.layerGround = layerGround;
    }

    public Integer getLayerUnderGround() {
        return layerUnderGround;
    }

    public void setLayerUnderGround(Integer layerUnderGround) {
        this.layerUnderGround = layerUnderGround;
    }

    public String getDesignOrg() {
        return designOrg;
    }

    public void setDesignOrg(String designOrg) {
        this.designOrg = designOrg;
    }

    public String getConstructionOrg() {
        return constructionOrg;
    }

    public void setConstructionOrg(String constructionOrg) {
        this.constructionOrg = constructionOrg;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getSupervisionOrg() {
        return supervisionOrg;
    }

    public void setSupervisionOrg(String supervisionOrg) {
        this.supervisionOrg = supervisionOrg;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPileConstructionOrg() {
        return pileConstructionOrg;
    }

    public void setPileConstructionOrg(String pileConstructionOrg) {
        this.pileConstructionOrg = pileConstructionOrg;
    }

    public String getPileModel() {
        return pileModel;
    }

    public void setPileModel(String pileModel) {
        this.pileModel = pileModel;
    }

    public String getPileDesignOrg() {
        return pileDesignOrg;
    }

    public void setPileDesignOrg(String pileDesignOrg) {
        this.pileDesignOrg = pileDesignOrg;
    }

    public Double getPileLength() {
        return pileLength;
    }

    public void setPileLength(Double pileLength) {
        this.pileLength = pileLength;
    }

    public Date getPileStartDate() {
        return pileStartDate;
    }

    public void setPileStartDate(Date pileStartDate) {
        this.pileStartDate = pileStartDate;
    }

    public Double getPileDiameter() {
        return pileDiameter;
    }

    public void setPileDiameter(Double pileDiameter) {
        this.pileDiameter = pileDiameter;
    }

    public Date getPileEndDate() {
        return pileEndDate;
    }

    public void setPileEndDate(Date pileEndDate) {
        this.pileEndDate = pileEndDate;
    }

    public String getSupportDesignOrg() {
        return supportDesignOrg;
    }

    public void setSupportDesignOrg(String supportDesignOrg) {
        this.supportDesignOrg = supportDesignOrg;
    }

    public String getSupportModel() {
        return supportModel;
    }

    public void setSupportModel(String supportModel) {
        this.supportModel = supportModel;
    }

    public String getSupportConstructionOrg() {
        return supportConstructionOrg;
    }

    public void setSupportConstructionOrg(String supportConstructionOrg) {
        this.supportConstructionOrg = supportConstructionOrg;
    }

    public Double getSupportLength() {
        return supportLength;
    }

    public void setSupportLength(Double supportLength) {
        this.supportLength = supportLength;
    }

    public Date getSupportStartDate() {
        return supportStartDate;
    }

    public void setSupportStartDate(Date supportStartDate) {
        this.supportStartDate = supportStartDate;
    }

    public Double getSupportDiameter() {
        return supportDiameter;
    }

    public void setSupportDiameter(Double supportDiameter) {
        this.supportDiameter = supportDiameter;
    }

    public Date getSupportEndDate() {
        return supportEndDate;
    }

    public void setSupportEndDate(Date supportEndDate) {
        this.supportEndDate = supportEndDate;
    }

    public String getSupStructure() {
        return supStructure;
    }

    public void setSupStructure(String supStructure) {
        this.supStructure = supStructure;
    }

    public Double getSpacing() {
        return spacing;
    }

    public void setSpacing(Double spacing) {
        this.spacing = spacing;
    }

    public String getRoadSettlement() {
        return roadSettlement;
    }

    public void setRoadSettlement(String roadSettlement) {
        this.roadSettlement = roadSettlement;
    }

    public String getPileDisplacement() {
        return pileDisplacement;
    }

    public void setPileDisplacement(String pileDisplacement) {
        this.pileDisplacement = pileDisplacement;
    }

    public String getBuildingSettlement() {
        return buildingSettlement;
    }

    public void setBuildingSettlement(String buildingSettlement) {
        this.buildingSettlement = buildingSettlement;
    }

    public Double getFoundationArea() {
        return foundationArea;
    }

    public void setFoundationArea(Double foundationArea) {
        this.foundationArea = foundationArea;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDrainageMode() {
        return drainageMode;
    }

    public void setDrainageMode(String drainageMode) {
        this.drainageMode = drainageMode;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public List<NewProFile> getNewProFileList() {
        return newProFileList;
    }

    public void setNewProFileList(List<NewProFile> newProFileList) {
        this.newProFileList = newProFileList;
    }
}
