package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.thinvent.nj.identify.entity.IdentifyContractFile;

/**
* T_IDENTIFY_CONTRACT_FILE Mapper
* @author wangwj
* @date 2018-12-4
*/
@Repository
public interface IdentifyContractFileMapper extends CURDMapper<IdentifyContractFile, String> {
    /**
     * 根据签订合同的ID获取合同附件列表
     * @param contractId
     * @return
     */
    List<IdentifyContractFile> getByContractId(String contractId);

    /**
     * 保存合同附件列表
     * @param totals
     */
    void insertFileList(List<IdentifyContractFile> totals);


}
