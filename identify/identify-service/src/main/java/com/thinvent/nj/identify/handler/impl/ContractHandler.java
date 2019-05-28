package com.thinvent.nj.identify.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.SpringContextUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.IdentifyContract;
import com.thinvent.nj.identify.entity.IdentifyContractFile;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.enums.RecordStatus;
import com.thinvent.nj.identify.handler.AbstractBusinessHandler;
import com.thinvent.nj.identify.mapper.IdentifyContractFileMapper;
import com.thinvent.nj.identify.mapper.IdentifyContractMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ContractHandler extends AbstractBusinessHandler {

    private IdentifyContractMapper contractMapper = SpringContextUtil.getBean(IdentifyContractMapper.class);

    private IdentifyContractFileMapper contractFileMapper = SpringContextUtil.getBean(IdentifyContractFileMapper.class);

    public ContractHandler(Map<String, Object> params) {
        super(params);
        mainId = (String) params.get("mainId");
    }

    @Override
    protected void save() {
        // 保存签订合同表
        saveContract();

        // 更新鉴定主表
        updateIdentifyMain();
    }

    /**
     * 保存签订合同表
     */
    private void saveContract() {
        // 签订合同
        IdentifyContract target = new IdentifyContract();
        MapperUtil.copy(params, target);
        target.setCreateUsername(curFullName);
        target.setCreateUser(curUserId);
        target.setCreateTime(new Date());
        contractMapper.insert(target);

        saveContractFileList(target.getId());
    }

    /**
     * @author : wangwj
     * @date :2018/12/04
     * @Description : 保存合同附件表
     */
    private void saveContractFileList(String id) {
        JSONArray contractFile = (JSONArray) params.get("contractFile");

        List<IdentifyContractFile> contractFileList = getContractFileList(contractFile, id);

        if (contractFileList.size() > 0) {
            contractFileMapper.insertFileList(contractFileList);
        }

    }

    /**
     * @author : wangwj
     * @date :2018/12/04
     * @Description : 获得合同附件列表
     */
    private List<IdentifyContractFile> getContractFileList(JSONArray photos, String contractId) {
        List<IdentifyContractFile> list = new ArrayList<>();
        if (photos == null) {
            return list;
        }

        IdentifyContractFile file;
        for (int index = 0; index < photos.size(); index++) {
            file = new IdentifyContractFile();
            file.setFileId(photos.getString(index));
            file.setContractId(contractId);
            file.setSort(index);
            file.setCreateTime(new Date());
            file.setDeletedFlag(Constant.DELETED_NO);
            list.add(file);
        }
        return list;
    }

    @Override
    protected void record() {
        internelRecord(RecordStatus.CONTRACT,null,(String) this.params.get("remarks"));
    }

    @Override
    protected void process() {
        completeTask(null);
    }

    private void updateIdentifyMain() {
        // 更新主表状态 -> 已签订合同
        IdentifyMain identifyMain = identifyService.get(mainId);
        identifyMain.setStatus(Constant.IDENTIFY_STATUS_HT);
        identifyMain.setOperatorTime(new Date());
        identifyService.update(identifyMain);
    }

}
