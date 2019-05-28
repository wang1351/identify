package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
* T_IDENTIFY_SIGN
* @author wangwj
* @date 2018-11-21
*/
public class IdentifySign extends BaseOracleEntity {

    // 房屋鉴定业务主表ID
    private String mainId;

    // 鉴定报告编制表ID
    private String reportId;

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
}
