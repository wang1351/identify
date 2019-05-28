package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.entity.IdentifyContract;
import com.thinvent.nj.identify.handler.impl.ContractHandler;
import com.thinvent.nj.identify.mapper.IdentifyContractMapper;
import com.thinvent.nj.identify.service.IdentifyContractService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * T_IDENTIFY_CONTRACT服务实现
 *
 * @author administrator
 * @date 2018-11-21
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IdentifyContractServiceImpl extends BaseCURDServiceImpl<IdentifyContract, String> implements IdentifyContractService {

    @Autowired
    private IdentifyContractMapper identifyContractMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {
        new ContractHandler(params).execute();
    }


    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取签订合同信息
     */
    @Override
    public IdentifyContract getContractByMainId(String mainId) {
        return identifyContractMapper.getIdentifyContractByMainId(mainId);
    }

}
