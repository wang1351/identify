package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
 * @author : xujc
 * @date :2018/11/14
 * @Description : 企业信用附件类
 */
public class OrgCreditFile extends BaseOracleEntity{
    // 企业信用评分ID (FK -> T_IDENTIFY_ORG_CREDIT_SCORE.ID)
    private String orgCreditScoreId;
    // 企业评价标准附件ID（FK -> T_BASE_FILE.ID)
    private String orgCreditFileId;

    public String getOrgCreditScoreId() {
        return orgCreditScoreId;
    }

    public void setOrgCreditScoreId(String orgCreditScoreId) {
        this.orgCreditScoreId = orgCreditScoreId;
    }

    public String getOrgCreditFileId() {
        return orgCreditFileId;
    }

    public void setOrgCreditFileId(String orgCreditFileId) {
        this.orgCreditFileId = orgCreditFileId;
    }
}
