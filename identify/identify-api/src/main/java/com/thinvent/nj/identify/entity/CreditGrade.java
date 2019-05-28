package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* 企业信用级别
* @author wangwj
* @date 2018-10-31
*/
public class CreditGrade extends BaseOracleEntity {

    // 级别名称
    private String name;

    // 大于等于（>=）
    private Integer get;

    // 小于
    private Integer let;

    // 备注
    private String remarks;

    // 序号
    private Integer seq;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGet() {
        return get;
    }

    public void setGet(Integer get) {
        this.get = get;
    }

    public Integer getLet() {
        return let;
    }

    public void setLet(Integer let) {
        this.let = let;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
