package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
* T_IDENTIFY_VERIFY
* @author wangwj
* @date 2018-11-21
*/
public class IdentifyVerify extends BaseOracleEntity {

    // 鉴定报告编制表ID
    private String reportId;

    // 审核不通过原因
    private String reason;

    // 审核结果（0：不通过；1通过）
    private Integer result;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
