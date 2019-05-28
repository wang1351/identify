package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;
import java.util.List;

/**
* T_IDENTIFY_PREVIEW
* @author wangwj
* @date 2018-11-21
*/
public class IdentifyPreview extends BaseOracleEntity {

    // 房屋鉴定业务主表ID
    private String mainId;

    // 房屋检查意见
    private String opinion;

    // 实施时间
    private Date implementTime;

    // 初勘附件列表
    private List<IdentifyPreviewFile> identifyPreviewFileList;


    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getImplementTime() {
        return implementTime;
    }

    public void setImplementTime(Date implementTime) {
        this.implementTime = implementTime;
    }

    public List<IdentifyPreviewFile> getIdentifyPreviewFileList() {
        return identifyPreviewFileList;
    }

    public void setIdentifyPreviewFileList(List<IdentifyPreviewFile> identifyPreviewFileList) {
        this.identifyPreviewFileList = identifyPreviewFileList;
    }
}
