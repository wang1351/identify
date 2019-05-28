package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.entity.IdentifyRecord;
import com.thinvent.nj.identify.entity.IdentifyReport;
import com.thinvent.nj.identify.entity.IdentifyVerify;
import com.thinvent.nj.identify.mapper.IdentifyRecordMapper;
import com.thinvent.nj.identify.mapper.IdentifyVerifyMapper;
import com.thinvent.nj.identify.service.IdentifyRecordService;
import com.thinvent.nj.identify.service.IdentifyReportService;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.identify.service.IdentifyVerifyService;
import com.thinvent.nj.mybatis.service.ProcessService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 鉴定报告审核服务实现
 *
 * @author liupj
 * @date 2018-11-21
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IdentifyVerifyServiceImpl extends BaseCURDServiceImpl<IdentifyVerify, String> implements IdentifyVerifyService {

    @Autowired
    private IdentifyVerifyMapper identifyVerifyMapper;

    @Resource(name = "mainProcessService")
    private ProcessService mainService;

    @Resource(name = "activeProcessService")
    private ProcessService activeService;

    @Autowired
    private IdentifyService identifyService;
    @Autowired
    private IdentifyRecordMapper identifyRecordMapper;

    @Autowired
    private IdentifyReportService reportService;

    @Autowired
    private IdentifyRecordService identifyRecordService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {
        String curFullName = (String) params.get("curFullName");
        String curUserId = (String) params.get("curUserId");
        // 鉴定报告ID
        String reportId = (String) params.get("reportId");
        // 0: 不通过；1：通过
        Integer result = (Integer) params.get("result");
        String mainId = (String) params.get("mainId");

        IdentifyMain identifyMain = identifyService.get(mainId);
        IdentifyVerify target = new IdentifyVerify();
        IdentifyRecord identifyRecord = new IdentifyRecord();
        // 审核表
        MapperUtil.copy(params, target);
        target.setCreateUsername(curFullName);
        target.setCreateUser(curUserId);
        target.setCreateTime(new Date());
        target.setReportId(reportId);


        Integer status;
        Integer recordStatus;
        if (result == 0) {
            status = Constant.IDENTIFY_STATUS_HT;
            recordStatus = Constant.IDENTIFY_STATUS_SHBG;
            // 不通过原因放在记录表
            target.setReason("");
            identifyRecord.setRemarks((String) params.get("reason"));

        } else if (result == 1) {
            status = Constant.IDENTIFY_STATUS_SH;
            recordStatus = status;
        } else {
            throw new IllegalArgumentException("wrong result parameter, it should be 0 or 1, but: now: " + result);
        }
        identifyVerifyMapper.insert(target);
        // 更新主表状态 -> 已审核或回退至已签订合同

        identifyMain.setStatus(status);
        identifyMain.setOperatorTime(new Date());
        identifyService.update(identifyMain);

        // 记录表
        identifyRecord.setStatus(recordStatus);
        identifyRecord.setOperatorUserName(curFullName);
        identifyRecord.setMainId(mainId);
        identifyRecord.setBusinessKey(target.getId());
        identifyRecord.setSort(identifyRecordMapper.generatorNextSort(mainId));
        identifyRecordService.insert(identifyRecord);

        // 完成任务
        IdentifyReport report = reportService.get(reportId);
        Integer activeIdentify = report.getActiveIdentify();
        // 是激活鉴定
        if (1 == activeIdentify) {
            String taskId = activeService.getTaskId(curUserId, mainId);
            Map<String, Object> variables = new HashMap<>(2);
            variables.put("result", result);

            activeService.complete(taskId, variables);
        } else {
            String taskId = mainService.getTaskId(curUserId, mainId);
            Map<String, Object> variables = new HashMap<>(2);
            variables.put("result", result);

            mainService.complete(taskId, variables);
        }
    }

}
