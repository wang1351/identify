package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xujc
 * @date :2018/11/14
 * @Description : 企业信用评分类
 */
public class OrgCreditScore extends BaseOracleEntity{
    // 企业信用id（外键）
    private String orgCreditId;
    // 企业评分类型（1：基本信息，2：良好信息，3：不良信息）
    private Integer orgScoreType;
    // 评分细则标准ID（FK -> T_IDENTIFY_SCORE_RULE_STANDARD.ID）
    private String scoreStandardId;
    // 评价标准次数
    private Integer scoreStandardNum;
    // 评价标准分值统计
    private Integer scoreStandardTotal;
    // 附件的id
    private List<String> orgCreditFileIds=new ArrayList<>();

    public String getOrgCreditId() {
        return orgCreditId;
    }

    public void setOrgCreditId(String orgCreditId) {
        this.orgCreditId = orgCreditId;
    }

    public Integer getOrgScoreType() {
        return orgScoreType;
    }

    public void setOrgScoreType(Integer orgScoreType) {
        this.orgScoreType = orgScoreType;
    }

    public String getScoreStandardId() {
        return scoreStandardId;
    }

    public void setScoreStandardId(String scoreStandardId) {
        this.scoreStandardId = scoreStandardId;
    }

    public Integer getScoreStandardNum() {
        return scoreStandardNum;
    }

    public void setScoreStandardNum(Integer scoreStandardNum) {
        this.scoreStandardNum = scoreStandardNum;
    }

    public Integer getScoreStandardTotal() {
        return scoreStandardTotal;
    }

    public void setScoreStandardTotal(Integer scoreStandardTotal) {
        this.scoreStandardTotal = scoreStandardTotal;
    }

    public List<String> getOrgCreditFileIds() {
        return orgCreditFileIds;
    }

    public void setOrgCreditFileIds(List<String> orgCreditFileIds) {
        this.orgCreditFileIds = orgCreditFileIds;
    }
}
