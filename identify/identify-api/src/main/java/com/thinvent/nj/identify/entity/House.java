/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : House.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.List;

/**
 * 房屋基本信息实体
 * @author panqh
 * @date 2018-11-13
 */
public class House extends BaseOracleEntity {

    /**
     * 鉴定主表ID
     */
    private String mainId;

    /**
     * 房屋所属区
     */
    private String zone;


    /**
     * 房屋所属街道
     */
    private String street;

    /**
     * 房屋地址
     */
    private String address;

    /**
     * 丘权号
     */
    private String hillock;

    /**
     * 房屋分栋信息列表
     */
    private List<HouseSplit> houseSplitList;

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
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

    public String getHillock() {
        return hillock;
    }

    public void setHillock(String hillock) {
        this.hillock = hillock;
    }

    public List<HouseSplit> getHouseSplitList() {
        return houseSplitList;
    }

    public void setHouseSplitList(List<HouseSplit> houseSplitList) {
        this.houseSplitList = houseSplitList;
    }

}
