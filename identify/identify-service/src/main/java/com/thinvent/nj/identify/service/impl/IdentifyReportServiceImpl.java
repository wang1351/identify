package com.thinvent.nj.identify.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.util.DateUtil;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.mapper.HouseSplitMapper;
import com.thinvent.nj.identify.mapper.IdentifyRecordMapper;
import com.thinvent.nj.identify.mapper.IdentifyReportDetailMapper;
import com.thinvent.nj.identify.mapper.IdentifyReportMapper;
import com.thinvent.nj.identify.service.IdentifyRecordService;
import com.thinvent.nj.identify.service.IdentifyReportService;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.mybatis.service.ProcessService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * T_IDENTIFY_REPORT服务实现
 *
 * @author liupj
 * @date 2018-11-21
 */
@Service
public class IdentifyReportServiceImpl extends BaseCURDServiceImpl<IdentifyReport, String> implements IdentifyReportService {

    @Autowired
    private IdentifyReportMapper identifyReportMapper;

    @Resource(name = "mainProcessService")
    private ProcessService mainService;

    @Resource(name = "activeProcessService")
    private ProcessService activeService;

    @Autowired
    private IdentifyService identifyService;

    @Autowired
    private IdentifyRecordMapper identifyRecordMapper;

    @Autowired
    private IdentifyRecordService identifyRecordService;

    @Autowired
    private HouseSplitMapper houseSplitMapper;

    @Autowired
    private IdentifyReportDetailMapper reportDetailMapper;

    @Autowired
    private DictService dictService;

    /**
     * 保存编制鉴定报告
     * @param params {
     *      curFullName,
     *      curUserId,
     *      method, -- 上传方式（1： 分栋上传；2：汇总上传）
     *      mainId,
     *      proName,
     *      identifyFile,
     *      testingFile,
     *      splitArray: [
     *          {
     *              id:,  -- 分栋ID
     *              identifyResult:,    -- 鉴定结果
     *              conclusion:,        -- 鉴定结论
     *              opinion:,           -- 处理意见
     *              proName:,           -- 项目名称
     *              identifyFile: [],      -- 鉴定报告文件ID
     *              testingFile:  []      -- 检测报告文件ID
     *          }
     *      ]
     * }
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {
        String curFullName = (String) params.get("curFullName");
        String curUserId = (String) params.get("curUserId");
        String mainId = (String) params.get("mainId");
        // 是否是激活流程
        boolean isActive = Boolean.parseBoolean((String)params.get("isActive"));

        // 更新主表状态 -> 已编制鉴定报告
        IdentifyMain identifyMain = identifyService.get(mainId);
        identifyMain.setStatus(Constant.IDENTIFY_STATUS_BG);
        identifyMain.setOperatorTime(new Date());
        identifyService.update(identifyMain);

        // 保存鉴定报告主表
        IdentifyReport target = new IdentifyReport();

        if (isActive) {
            target.setActiveIdentify(1);
        } else {
            target.setActiveIdentify(0);
        }

        MapperUtil.copy(params, target);

        DictItem identifyContent = dictService.getItemByGroupKeyAndValue(Constant.IDENTIFY_TYPE_KEY, identifyMain.getContent());
        String content = identifyContent.getName();
        // 当鉴定内容为“其他”时，补充后面的输入框值
        if ("8".equals(identifyMain.getContent())) {
            content = identifyMain.getOtherContent();
        }

        String caseNo = identifyMain.getCaseNo();
        String identifyType = content;
        String clientName = identifyMain.getClient().getClientName();
        String identifyOrgName = identifyMain.getOrgName();

        // 当前是汇总上传，鉴定报告和检测报告合并上传
        if (target.getMethod().equals(Constant.REPORT_METHOD_ALL)) {
            target.setProName((String) params.get("proName"));
            target.setCaseNo(caseNo);
            target.setIdentifyType(identifyType);
            target.setReportDate(new Date());
            target.setClientName(clientName);
            target.setIdentifyOrgName(identifyOrgName);
            // TODO: 设置二维码附件ID
            target.setQrFileId((String) params.get("qrCode"));

            // 鉴定报告文件ID
            JSONArray identifyFiles = (JSONArray) params.get("identifyFile");
            // 检测报告文件ID
            JSONArray testingFiles = (JSONArray) params.get("testingFile");

            target.setIdentifyFileId(identifyFiles.getString(0));
            target.setTestingFileId(testingFiles.getString(0));
        }

        target.setCreateUsername(curFullName);
        target.setCreateUser(curUserId);
        target.setCreateTime(new Date());
        identifyReportMapper.insert(target);

        // 保存鉴定报告明细表
        JSONArray splitArray = (JSONArray) params.get("splitArray");
        List<IdentifyReportDetail> reportDetails = new ArrayList<>(splitArray.size());

        JSONObject jsonObject;
        for (int index = 0; index < splitArray.size(); index ++) {
            jsonObject = splitArray.getJSONObject(index);
            String splitId = jsonObject.getString("id");
            String identifyResult = jsonObject.getString("identifyResult");
            String conclusion = jsonObject.getString("conclusion");
            String opinion = jsonObject.getString("opinion");
            String proName = jsonObject.getString("proName");

            IdentifyReportDetail reportDetail = new IdentifyReportDetail();
            reportDetail.setReportId(target.getId());
            reportDetail.setHouseSplitId(splitId);
            reportDetail.setHouseName(houseSplitMapper.get(splitId).getHouseName());
            reportDetail.setIdentifyResult(identifyResult);
            reportDetail.setConclusion(conclusion);
            reportDetail.setOpinion(opinion);
            reportDetail.setSort(index);

            // 当前是分栋上传，每个分栋分别保存鉴定报告和检测报告
            if (target.getMethod().equals(Constant.REPORT_METHOD_SPLIT)) {
                reportDetail.setProName(proName);
                reportDetail.setCaseNo(caseNo);
                reportDetail.setIdentifyType(identifyType);
                reportDetail.setReportDate(new Date());
                reportDetail.setClientName(clientName);
                reportDetail.setIdentifyOrgName(identifyOrgName);

                // TODO: 设置二维码附件ID
                reportDetail.setQrFileId("");

                JSONArray identifyFileIds = jsonObject.getJSONArray("identifyFile");
                String identifyFileId = identifyFileIds.getString(0);
                reportDetail.setIdentifyFileId(identifyFileId);

                JSONArray testingFileIds = jsonObject.getJSONArray("testingFile");
                String testingFileId = testingFileIds.getString(0);
                reportDetail.setTestingFileId(testingFileId);
            }

            reportDetails.add(reportDetail);
        }

       reportDetailMapper.insertList(reportDetails);

        // 流程记录表
        Map<String, Object> recordPrams = new HashMap<>(6);
        recordPrams.put("mainId", mainId);
        recordPrams.put("curFullName", curFullName);
        recordPrams.put("status", Constant.IDENTIFY_STATUS_BG);
        recordPrams.put("businessKey", target.getId());
        identifyRecordService.insert(recordPrams);


        if (isActive) {
            Map<String, Object> param = new HashMap<>(2);
            param.put("businessKey", mainId);
            param.put("startUserId", curUserId);
            activeService.start(param);

            String taskId = activeService.getTaskId(curUserId, mainId);
            activeService.claim(taskId, curUserId);

            activeService.complete(taskId, new HashMap<>());

        } else {
            // 完成任务
            String taskId = mainService.getTaskId(curUserId, target.getMainId());
            mainService.complete(taskId, new HashMap<>(1));
        }


    }

    @Override
    public IdentifyReport getByMainId(String mainId) {
        List<IdentifyReport> reports = identifyReportMapper.getIdentifyReportByMainId(mainId);

        IdentifyReport identifyReport = null;

        for (IdentifyReport report : reports) {
            IdentifyVerify identifyVerify = report.getIdentifyVerify();
            // 选择没有审核记录的报告对象
            if (identifyVerify == null) {
                identifyReport = report;
                break;
            } else {
                Integer result = identifyVerify.getResult();
                // 选择通过审核的报告对象
                if (result == 1) {
                    identifyReport = report;
                    break;
                }
            }

        }
        return identifyReport;
    }
}

