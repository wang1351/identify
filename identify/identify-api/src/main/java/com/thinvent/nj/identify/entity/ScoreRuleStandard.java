/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ScoreRuleStandard.java
 * 功能概要       :
 * 做成日期       : 2018-11-06・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * 评分细则评分标准实体类
 * @author panqh
 * @date 2018-11-06
 */
public class ScoreRuleStandard extends BaseOracleEntity {

    /**
     * 评分细则ID
     */
    private String scoreRuleId;

    /**
     * 评分项目
     */
    private String scoreItem;

    /**
     * 评分标准
     */
    private String scoreStandard;

    /**
     * 评分分值
     */
    private String scoreValue;

    /**
     * 排序
     */
    private Integer sort;

    //add by wangwj 20190121 start

    /**
     *评分标准详细
     */
    private  String scoreStandardDetail;

    public String getScoreStandardDetail() {
        return scoreStandardDetail;
    }

    public void setScoreStandardDetail(String scoreStandardDetail) {
        this.scoreStandardDetail = scoreStandardDetail;
    }

    //add by wangwj 20190121 end
    public String getScoreRuleId() {
        return scoreRuleId;
    }

    public void setScoreRuleId(String scoreRuleId) {
        this.scoreRuleId = scoreRuleId;
    }

    public String getScoreItem() {
        return scoreItem;
    }

    public void setScoreItem(String scoreItem) {
        this.scoreItem = scoreItem;
    }

    public String getScoreStandard() {
        return scoreStandard;
    }

    public void setScoreStandard(String scoreStandard) {
        this.scoreStandard = scoreStandard;
    }

    public String getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(String scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
