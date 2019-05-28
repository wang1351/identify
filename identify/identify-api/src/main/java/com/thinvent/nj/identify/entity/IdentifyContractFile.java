package com.thinvent.nj.identify.entity;


import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

/**
* T_IDENTIFY_CONTRACT_FILE
* @author wangwj
* @date 2018-12-4
*/
public class IdentifyContractFile extends BaseOracleEntity {

    // 排序号
    private Integer sort;

    // 文件ID
    private String fileId;

    // 签订合同表ID
    private String contractId;



    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
