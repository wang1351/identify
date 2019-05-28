/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ScoreRule.java
 * 功能概要       :
 * 做成日期       : 2018-11-06・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * 评分细则实体类
 * @author panqh
 * @date 2018-11-06
 */
public class ScoreRule extends BaseOracleEntity {

    /**
     * 评分类型（1：基本信息，2：良好信息，3：不良信息）
     */
    private Integer scoreType;

    /**
     * 评分项目
     */
    private String scoreItem;

    /**
     * 排序
     */
    private Integer sort;


    public Integer getScoreType() {
        return scoreType;
    }

    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    public String getScoreItem() {
        return scoreItem;
    }

    public void setScoreItem(String scoreItem) {
        this.scoreItem = scoreItem;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
