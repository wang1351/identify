package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.mapper.IdentifyRecordMapper;
import com.thinvent.nj.identify.mapper.IdentifySendMapper;
import com.thinvent.nj.identify.service.*;
import com.thinvent.nj.mybatis.service.ProcessService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 鉴定报告发送服务实现
 *
 * @author liupj
 * @date 2018-11-21
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IdentifySendServiceImpl extends BaseCURDServiceImpl<IdentifySend, String> implements IdentifySendService {

    @Autowired
    private IdentifySendMapper identifySendMapper;

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

    @Autowired
    private IdentifyContractService contractService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {
        String curFullName = (String) params.get("curFullName");
        String curUserId = (String) params.get("curUserId");
        String mainId = (String) params.get("mainId");

        // 更新主表状态 -> 已发送
        IdentifyMain identifyMain = identifyService.get(mainId);
        identifyMain.setStatus(Constant.IDENTIFY_STATUS_FF);
        identifyMain.setOperatorTime(new Date());

        //签订合同实体
        IdentifyContract contract = contractService.getContractByMainId(mainId);

        // 未完成鉴定次数
        if (identifyMain.getUnfinishedCount() == null) {
            identifyMain.setUnfinishedCount(contract.getIdentifyCount() - 1);
        } else {
            identifyMain.setUnfinishedCount(identifyMain.getUnfinishedCount() - 1);
        }

        identifyService.update(identifyMain);

        IdentifySend target = new IdentifySend();
        MapperUtil.copy(params, target);

        target.setCreateUsername(curFullName);
        target.setCreateUser(curUserId);
        target.setCreateTime(new Date());

        IdentifyReport finalReport = reportService.getByMainId(mainId);

        target.setReportId(finalReport.getId());
        identifySendMapper.insert(target);

        // 流程记录表
        Map<String, Object> recordPrams = new HashMap<>(6);
        recordPrams.put("mainId", mainId);
        recordPrams.put("curFullName", curFullName);
        recordPrams.put("status", Constant.IDENTIFY_STATUS_FF);
        recordPrams.put("businessKey",target.getId());
        identifyRecordService.insert(recordPrams);

        // 完成任务
        IdentifyReport report = reportService.get(target.getReportId());
        Integer activeIdentify = report.getActiveIdentify();
        // 是激活鉴定
        if (1 == activeIdentify) {
            String taskId = activeService.getTaskId(curUserId, mainId);
            activeService.complete(taskId, new HashMap<>());
        } else {
            String taskId = mainService.getTaskId(curUserId, mainId);
            mainService.complete(taskId, new HashMap<>());
        }
    }

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取鉴定报告签发放信息.列表详细用
     */
    @Override
    public IdentifySend getSendByMainId(String mainId) {
        return identifySendMapper.getIdentifySendByMainId(mainId);
    }
}
