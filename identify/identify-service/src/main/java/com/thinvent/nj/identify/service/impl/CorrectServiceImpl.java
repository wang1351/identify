/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectServiceImpl.java
 * 功能概要       :
 * 做成日期       : 2018-12-05・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.mapper.*;
import com.thinvent.nj.identify.service.CorrectService;
import com.thinvent.nj.mybatis.service.ProcessService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
* 补正业务表服务实现
* @author panqh
* @date 2018-12-05
*/
@Service
public class CorrectServiceImpl extends BaseCURDServiceImpl<Correct, String> implements CorrectService {

    @Autowired
    private IdentifyMainMapper identifyMainMapper;

    @Autowired
    private CorrectMapper correctMapper;

    @Autowired
    private CorrectFileMapper correctFileMapper;

    @Autowired
    private IdentifyCorrectRecordMapper identifyCorrectRecordMapper;

    @Autowired
    private CorrectVerifyMapper correctVerifyMapper;
    @Autowired
    private CorrectUploadMapper correctUploadMapper;

    // 工作流服务注入
    @Resource(name = "correctProcessService")
    private ProcessService correctService;

    @Override
    public List<Correct> findList(Map<String, Object> params) {

        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", params.get("curUserId"));
        Map<String, Object> map = MapperUtil.convertToMap(correctService.getMyTasks(condition));

        Map<String, Object> keyTaskIdMap = (Map<String, Object>) map.get("data");
        params.put("correctIdList", keyTaskIdMap.keySet());

        return super.findList(params);
    }

    /**
     * 保存补正变更申请信息
     * @author panqh
     * @date 2018-12-05
     * @param mainId
     * @param params
     */
    @Transactional
    @Override
    public void saveCorrectRequestInfo(String mainId, Map<String, Object> params) {

        // 更新鉴定主表
        IdentifyMain identifyMain = identifyMainMapper.get(mainId);
        identifyMain.setIsCorrect(Constant.IDENTIFY_CORRECT_YES);
        identifyMainMapper.update(identifyMain);

        // 保存补正变更信息
        Map<String,Object> map =(Map<String, Object>) params.get("params");
        Correct correct = new Correct();
        MapperUtil.copy(map,correct);
        correct.setMainId(mainId);
        correct.setStatus(Constant.CORRECT_STATUS_YSQ);
        correct.setSuspend(Constant.IDENTIFY_PROCESS_RUNNING);
        JSONArray reportDetailArray = (JSONArray) map.get("reportDetailId");
        String reportDetailIds = "";
        for (int j = 0; j < reportDetailArray.size(); j++) {
            String reportDetailId = reportDetailArray.getString(j);
            reportDetailIds += reportDetailId + ",";
        }
        if (!StringUtil.isNullOrEmpty(reportDetailIds)) {
            reportDetailIds = reportDetailIds.substring(0, reportDetailIds.length() - 1);
        }
        correct.setReportDetailIds(reportDetailIds);
        correctMapper.insert(correct);
        // 保存记录表信息
        IdentifyCorrectRecord identifyCorrectRecord = new IdentifyCorrectRecord();
        identifyCorrectRecord.setCorrectId(correct.getId());
        identifyCorrectRecord.setOperatorUserName((String) params.get("curUserName"));
        identifyCorrectRecord.setOperatorTime(new Date());
        identifyCorrectRecord.setSort(identifyCorrectRecordMapper.generatorNextSort(correct.getId()));
        identifyCorrectRecord.setStatus(Constant.CORRECT_STATUS_YSQ);
        identifyCorrectRecordMapper.insert(identifyCorrectRecord);

        // 保存工作流信息
        Map<String, Object> param = new HashMap<>(2);
        param.put("businessKey", correct.getId());
        param.put("startUserId", params.get("curUserId"));
        correctService.start(param);
    }

    /**
     * 保存补正变更审核信息
     * @author panqh
     * @date 2018-12-05
     * @param params
     */
    @Transactional
    @Override
    public void saveCorrectCheckInfo(Map<String, Object> params) {
        String correctId = (String) params.get("correctId");
        String checkResult = (String) params.get("checkResult");
        String checkOpinion = (String) params.get("checkOpinion");

        String curUserId = (String) params.get("curUserId");
        String curUserName = (String) params.get("curUserName");


        Correct correct = correctMapper.get(correctId);


        IdentifyCorrectRecord identifyCorrectRecord = new IdentifyCorrectRecord();


        CorrectVerify correctVerify =new CorrectVerify();
        Integer recordStatus;
        Integer status;
        // 审核不通过
        if("2".equals(checkResult)){
            correct.setStatus(Constant.CORRECT_STATUS_SHBTG);
            recordStatus = Constant.CORRECT_STATUS_SHBTG;
            status = 0;
            identifyCorrectRecord.setRemarks(checkOpinion);

        }else {
            correct.setStatus(Constant.CORRECT_STATUS_YSH);
            recordStatus = Constant.CORRECT_STATUS_YSH;
            status = 1;
            correctVerify.setCheckOpinion(checkOpinion);
        }
        // 更新补正变更信息
        correctMapper.update(correct);

        // 保存记录表信息
        identifyCorrectRecord.setCorrectId(correct.getId());
        identifyCorrectRecord.setOperatorUserName(curUserName);
        identifyCorrectRecord.setOperatorTime(new Date());
        identifyCorrectRecord.setSort(identifyCorrectRecordMapper.generatorNextSort(correct.getId()));
        identifyCorrectRecord.setStatus(recordStatus);
        identifyCorrectRecordMapper.insert(identifyCorrectRecord);

        // 保存审核表
        correctVerify.setCheckResult(Integer.parseInt(checkResult));

        correctVerify.setCorrectId(correctId);
        correctVerifyMapper.insert(correctVerify);

        // 保存工作流信息
        String taskId = correctService.getTaskId(curUserId, correctId);
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("status", status);
        correctService.complete(taskId, variables);
    }

    /**
     * 保存补正变更上传资料信息
     * @author panqh
     * @date 2018-12-05
     * @param params
     */
    @Transactional
    @Override
    public void saveCorrectUploadInfo(Map<String, Object> params) {
        String correctId = (String) params.get("correctId");
        String description = (String) params.get("description");
        JSONArray fileIdS = (JSONArray) params.get("fileIdS");

        String curUserId = (String) params.get("curUserId");
        String curUserName = (String) params.get("curUserName");

        // 更新补正变更信息
        Correct correct = correctMapper.get(correctId);
        correct.setStatus(Constant.CORRECT_STATUS_YBZ);

        correctMapper.update(correct);
        // 保存上传附件表
        CorrectUpload correctUpload = new CorrectUpload();
        correctUpload.setCorrectId(correctId);
        correctUpload.setDescription(description);
        correctUploadMapper.insert(correctUpload);

        // 保存补正变更附件信息
        List<CorrectFile> lstCorrectFile = new ArrayList<>();
        CorrectFile correctFile = null;
        if (fileIdS != null && fileIdS.size() > 0) {
            for (int i = 0; i < fileIdS.size(); i++) {
                correctFile = new CorrectFile();
                correctFile.setCorrectId(correctId);
                correctFile.setFileId(fileIdS.getString(i));
                correctFile.setSort(i + 1);

                lstCorrectFile.add(correctFile);
            }
        }


        correctFileMapper.insertCorrectFileList(lstCorrectFile);

        // 保存记录表信息
        IdentifyCorrectRecord identifyCorrectRecord =new IdentifyCorrectRecord();
        identifyCorrectRecord.setCorrectId(correct.getId());
        identifyCorrectRecord.setOperatorUserName(curUserName);
        identifyCorrectRecord.setOperatorTime(new Date());
        identifyCorrectRecord.setSort(identifyCorrectRecordMapper.generatorNextSort(correct.getId()));
        identifyCorrectRecord.setStatus(Constant.CORRECT_STATUS_YBZ);
        identifyCorrectRecordMapper.insert(identifyCorrectRecord);

        // 保存工作流信息
        String taskId = correctService.getTaskId(curUserId, correctId);
        correctService.complete(taskId, new HashMap<>(1));
    }


}
