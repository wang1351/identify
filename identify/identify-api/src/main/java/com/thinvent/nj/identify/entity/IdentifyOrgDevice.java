package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* 使用设备信息表
* @author wangwj
* @date 2019-3-5
*/
//add by wangwj 20190305 start
public class IdentifyOrgDevice extends BaseOracleEntity {

    // 鉴定机构ID
    private String orgId;

    // 检测设备
    private String name;

    // 序号
    private Integer seq;

    // 编号
    private String num;

    // 备注
    private String remarks;

    // 规格型号
    private String specification;



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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
//add by wangwj 20190305 end