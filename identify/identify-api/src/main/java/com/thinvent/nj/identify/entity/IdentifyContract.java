package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.List;

/**
* T_IDENTIFY_CONTRACT
* @author wangwj
* @date 2018-11-21
*/
public class IdentifyContract extends BaseOracleEntity {

    // 房屋鉴定业务主表ID
    private String mainId;

    // 合同金额
    private Double contractAmount;

    // 鉴定次数
    private Integer identifyCount;

    // 签订合同文件ID
    private String fileId;

    //合同附件表列表
    private List<IdentifyContractFile> identifyContractFileList;

    public List<IdentifyContractFile> getIdentifyContractFileList() {
        return identifyContractFileList;
    }

    public void setIdentifyContractFileList(List<IdentifyContractFile> identifyContractFileList) {
        this.identifyContractFileList = identifyContractFileList;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getIdentifyCount() {
        return identifyCount;
    }

    public void setIdentifyCount(Integer identifyCount) {
        this.identifyCount = identifyCount;
    }
}
