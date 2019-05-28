package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

/**
* T_IDENTIFY_PLAN
* @author wangwj
* @date 2018-11-21
*/
public class IdentifyPlan extends BaseOracleEntity {

    // 房屋鉴定主表ID
    private String mainId;

    // 出具方案文件ID
    private String fileId;

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
