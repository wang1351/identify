/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : IdentifyServiceImpl.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.util.DateUtil;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.handler.BusinessHandler;
import com.thinvent.nj.identify.handler.impl.RequestHandler;
import com.thinvent.nj.identify.mapper.*;
import com.thinvent.nj.identify.service.IdentifyMainService;
import com.thinvent.nj.identify.service.IdentifyRecordService;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.mybatis.service.ProcessService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.entity.Org;
import com.thinvent.nj.uc.service.DictService;
import com.thinvent.nj.uc.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 鉴定服务接口实现
 * @author panqh
 * @date 2018-11-13
 */
@Service
public class IdentifyServiceImpl extends BaseCURDServiceImpl<IdentifyMain, String> implements IdentifyService {

    @Autowired
    private IdentifyMainMapper identifyMapper;

    @Autowired
    private IdentifyMainService identifyMainService;

    @Autowired
    private IdentifyReportMapper reportMapper;

    @Autowired
    private IdentifyReportDetailMapper reportDetailMapper;

    @Autowired
    private DictService dictService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private IdentifyRecordService recordService;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseSplitMapper houseSplitMapper;

    @Autowired
    private HouseSplitFileMapper houseSplitFileMapper;

    @Autowired
    protected ClientMapper clientMapper;

    @Autowired
    protected ClientFileMapper clientFileMapper;
    @Autowired
    private IdentifyApplyMapper identifyApplyMapper;

    @Resource(name = "mainProcessService")
    private ProcessService mainService;

    @Resource(name = "activeProcessService")
    private ProcessService activeService;

    @Override
    public List<IdentifyMain> findList(Map<String, Object> params) {

        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", params.get("userId"));

        Map<String, Object> map = MapperUtil.convertToMap(mainService.getMyTasks(condition));
        Map<String, Object> map2 = MapperUtil.convertToMap(activeService.getMyTasks(condition));

        Map<String, Object> keyTaskIdMap = (Map<String, Object>)map.get("data");
        Map<String, Object> keyTaskIdMap2 = (Map<String, Object>)map2.get("data");

        Set<String> mainIdSet = new HashSet<>(keyTaskIdMap.size() + keyTaskIdMap2.size());
        mainIdSet.addAll(keyTaskIdMap.keySet());
        mainIdSet.addAll(keyTaskIdMap2.keySet());

        params.put("mainIdList", mainIdSet);

            return super.findList(params);


    }

    /**
     * 生成编号
     * @author panqh
     * @date 2018-11-13
     * @return
     */
    public String generateCaseNumber() {
        String formatterDate = DateUtil.toChar(new Date(), "yyyyMMdd");
        String caseNo = identifyMapper.getMaxCaseNo(formatterDate);

        String result;
        if (StringUtil.isNullOrEmpty(caseNo)) {
            result = formatterDate + "001";
        } else {
            if (caseNo.length() > 11) {
                caseNo = caseNo.substring(0, 11);
            }
            result = String.valueOf(Long.parseLong(caseNo) + 1);
        }

        return result;
    }

    /**
     * 保存鉴定申请页面
     * @author panqh
     * @date 2018-11-19
     * @param params
     */
    @Transactional
    @Override
    public void saveIdentifyRequestInfo(Map<String, Object> params) {
        String genNo = generateCaseNumber();
        // 手机申请表id
        String id = (String)params.get("id");
        if(!StringUtil.isNullOrEmpty(id)){
            IdentifyApply identifyApply = identifyApplyMapper.get(id);
            identifyApply.setIdentifyStatue(Constant.PHONE_APPLY_YSQ);
            identifyApplyMapper.update(identifyApply);
            params.put("source",identifyApply.getIdentifySource());
            params.put("curOrgId",identifyApply.getIdentifyOrgId());
        }else {
            params.put("source",0);
        }
        // 房屋基本信息表
        JSONArray houseArray = (JSONArray) params.get("house");
        boolean isMulti = houseArray.size() > 1;

        for (int index = 0; index < houseArray.size(); index ++) {
            params.put("caseNo", isMulti ? genNo + "-" + (index + 1) : genNo);
            JSONObject houseObject = houseArray.getJSONObject(index);
            JSONArray splitArray = houseObject.getJSONArray("splitArray");

            params.put("houseObject", houseObject);
            params.put("splitArray", splitArray);

            BusinessHandler businessHandler = new RequestHandler(params);
            businessHandler.execute();
        }

    }


    /**
     * 根据主键获取鉴定信息
     * @author panqh
     * @date 2018-11-20
     * @param mainId
     * @return
     */
    @Override
    public String getIdentifyInfo(String mainId) {
        JSONObject result = new JSONObject();

        // 鉴定主表内容
        JSONObject identifyObject = new JSONObject();
        IdentifyMain identifyMain = identifyMainService.get(mainId);
        DictItem identifyContent = dictService.getItemByGroupKeyAndValue(Constant.IDENTIFY_TYPE_KEY, identifyMain.getContent());
        String content = identifyContent.getName();
        // 当鉴定内容为“其他”时，补充后面的输入框值
        if ("8".equals(identifyMain.getContent())) {
            identifyObject.put("content", content + "-" + identifyMain.getOtherContent());
        } else {
            identifyObject.put("content", content);
        }

        Org org = orgService.get(identifyMain.getOrgId());
        identifyObject.put("identifyOrg", org.getFullName());
        result.put("identify", identifyObject);

        // 委托人信息
        JSONObject ClientObject = new JSONObject();
        Client client = clientMapper.getClientByMainId(mainId);
        ClientObject.put("clientName", client.getClientName());
        ClientObject.put("contactName", client.getContactName());
        ClientObject.put("phone", client.getPhone());
        if (Constant.CLIENT_NATURE_CQR.equals(client.getNature())) {
            ClientObject.put("nature", "产权人");
        } else if (Constant.CLIENT_NATURE_SYR.equals(client.getNature())) {
            ClientObject.put("nature", "使用人");
        } else {
            ClientObject.put("nature", "第三方");
        }
        ClientObject.put("content", client.getContent());
        ClientObject.put("condition", client.getCondition());
        List<ClientFile> lstClientFile = clientFileMapper.getClientFileList(client.getId());
        int cqzNum = 0;
        int IDNum = 0;
        int qtNum = 0;
        for (ClientFile clientFile : lstClientFile ) {
            if (Constant.CLIENT_FILE_TYPE_CQZ.equals(clientFile.getType())) {
                cqzNum = cqzNum + 1;
            } else if (Constant.CLIENT_FILE_TYPE_ID.equals(clientFile.getType())) {
                IDNum = IDNum + 1;
            } else {
                qtNum = qtNum + 1;
            }
        }

        ClientObject.put("cqzFile", "房产证*" + cqzNum);
        ClientObject.put("IDFile", "身份证*" + IDNum);
        ClientObject.put("qtFile", "委托书*" + qtNum);
        result.put("client", ClientObject);

        // 房屋信息
        JSONObject houseObject = new JSONObject();
        House house = houseMapper.getHouseByMainId(mainId);
        houseObject.put("houseAddress", house.getZone() + house.getStreet() + house.getAddress());
        houseObject.put("hillock", house.getHillock());
        List<HouseSplit> lstHouseSplit = houseSplitMapper.getHouseSplitListByHouseId(house.getId());
        JSONArray houseSplitArray = new JSONArray();
        JSONObject houseSplitObject = null;
        for (HouseSplit houseSplit : lstHouseSplit) {
            houseSplitObject = new JSONObject();
            houseSplitObject.put("houseName", houseSplit.getHouseName());
            DictItem houseStructure = dictService.getItemByGroupKeyAndValue(Constant.HOUSE_STRUCTURE_KEY, houseSplit.getStructure());
            houseSplitObject.put("structure", houseStructure.getName());
            //add by wangwj 20181221 start
            houseSplitObject.put("layerAbove", houseSplit.getLayerAbove());
            houseSplitObject.put("layerUnder", houseSplit.getLayerUnder());
            //add by wangwj 20181221 end
            houseSplitObject.put("buildYear", houseSplit.getBuildYear());
            houseSplitObject.put("purpose", houseSplit.getPurpose());
            houseSplitObject.put("area", houseSplit.getArea());
            houseSplitObject.put("identifyArea", houseSplit.getIdentifyArea());
            houseSplitObject.put("position", houseSplit.getPosition());
            houseSplitObject.put("nature", houseSplit.getNature());
            houseSplitObject.put("holderPerson", houseSplit.getHolderPerson());
            houseSplitObject.put("person", houseSplit.getPerson());
            houseSplitObject.put("constructOrg", houseSplit.getConstructOrg());
            houseSplitObject.put("designOrg", houseSplit.getDesignOrg());

            List<HouseSplitFile> lstSplitFile = houseSplitFileMapper.getSplitFileListBySplitId(houseSplit.getId());
            houseSplitObject.put("splitFile", "图纸*" + lstSplitFile.size());

            houseSplitArray.add(houseSplitObject);
        }

        houseObject.put("houseSplitInfo", houseSplitArray);

        result.put("house", houseObject);

        return JSON.toJSONString(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateMainStatus(String id, Integer status) {
        identifyMapper.updateMainStatus(id, status);
    }

    @Override
    public void updateMainProcessStatus(String id, Integer status) {
        identifyMapper.updateMainProcessStatus(id, status);
    }

    @Override
    public void claim(String mainId, String userId) {
        String taskId = mainService.getTaskId(userId, mainId);

        if (StringUtil.isNullOrEmpty(taskId)) {
            return;
        }

        mainService.claim(taskId, userId);
    }

    @Override
    public String getTaskId(String mainId, String userId) {
        return mainService.getTaskId(userId, mainId);
    }

    @Override
    public String getInstanceId(String mainId, String userId) {
        Map<String, Object> map = MapperUtil.convertToMap(mainService.getTask(getTaskId(mainId, userId)));
        return (String)map.get("processInstanceId");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void stopSuspendOrResumeProcess(Map<String, Object> params) {
        String mainId = (String) params.get("mainId");
        String curUserId = (String) params.get("curUserId");
        String curFullName = (String) params.get("curFullName");
        String action = (String) params.get("action");
        String reason = (String) params.get("reason");

        String instanceId = mainService.getInstanceIdByBusinessKey(mainId, null);

        // 保存流程记录表
        Map<String, Object> param = new HashMap<>(6);
        param.put("mainId", mainId);
        param.put("curUserId", curUserId);
        param.put("curFullName", curFullName);
        param.put("remarks", reason);


        if (action.equals("stop")) {
            mainService.delProcessInstance(instanceId);
            updateMainProcessStatus(mainId, Constant.IDENTIFY_PROCESS_STOP);
            param.put("status", Constant.IDENTIFY_STATUS_ENDING);
        } else if (action.equals("suspend")) {
            mainService.suspendProcessInstance(instanceId);
            updateMainProcessStatus(mainId, Constant.IDENTIFY_PROCESS_SUSPEND);
            param.put("status", Constant.IDENTIFY_STATUS_SUSPEND);
        } else if (action.equals("resume")) {
            mainService.activeProcessInstance(instanceId);
            updateMainProcessStatus(mainId, Constant.IDENTIFY_PROCESS_RUNNING);
            param.put("status", Constant.IDENTIFY_STATUS_RELEASE_SUSPEND);
        } else {
            throw new IllegalArgumentException("action can only be stop or suspend , but now : " + action);
        }


        recordService.insert(param);
    }





    /**
     * 根据鉴定主键获取鉴定危房信息
     * @author panqh
     * @date 2018-12-12
     * @param reportDetailId
     * @return
     */
    @Override
    public String getIdentifyDangerHouseDetail(String reportDetailId) {
        JSONObject result = new JSONObject();
        // 获取鉴定报告明细实体
        IdentifyReportDetail reportDetail = reportDetailMapper.get(reportDetailId);
        // 获取鉴定报告实体
        IdentifyReport report = reportMapper.get(reportDetail.getReportId());
        // 鉴定主表主键
        String identifyId = report.getMainId();
        // 房屋分栋ID
        String houseSplitId = reportDetail.getHouseSplitId();

        // 编号
        IdentifyMain identify = identifyMapper.get(identifyId);
        result.put("caseNo", identify.getCaseNo());
        // 鉴定报告
        Integer method = report.getMethod();
        if (Constant.REPORT_METHOD_SPLIT.equals(method)) {
            result.put("identifyFileId", reportDetail.getIdentifyFileId());
            result.put("testingFileId", reportDetail.getTestingFileId());
        } else {
            result.put("identifyFileId", report.getIdentifyFileId());
            result.put("testingFileId", report.getTestingFileId());
        }
        DictItem identifyResult = dictService.getItemByGroupKeyAndValue(Constant.HOUSE_IDENTIFY_RESULT, reportDetail.getIdentifyResult());
        // 鉴定结果
        result.put("identifyResult", identifyResult.getName());
        // 鉴定结论
        result.put("conclusion", reportDetail.getConclusion());
        // 处理意见
        result.put("opinion", reportDetail.getOpinion());

        // 申请人信息
        Client client = clientMapper.getClientByMainId(identifyId);
        // 委托人
        result.put("clientName", client.getClientName());
        // 联系人
        result.put("contactName", client.getContactName());
        // 联系电话
        if (!StringUtil.isNullOrEmpty(client.getPhone()) && !StringUtil.isNullOrEmpty(client.getPhone2())) {
            result.put("phone", client.getPhone() + ", " + client.getPhone2());
        } else if (!StringUtil.isNullOrEmpty(client.getPhone()) && StringUtil.isNullOrEmpty(client.getPhone2())) {
            result.put("phone", client.getPhone());
        } else if (StringUtil.isNullOrEmpty(client.getPhone()) && !StringUtil.isNullOrEmpty(client.getPhone2())) {
            result.put("phone", client.getPhone2());
        } else {
            result.put("phone", "");
        }

        // 房屋信息
        House house = houseMapper.getHouseByMainId(identifyId);
        result.put("zone", house.getZone());
        result.put("street", house.getStreet());
        result.put("address", house.getZone() + house.getStreet() + house.getAddress());
        HouseSplit houseSplit = houseSplitMapper.get(houseSplitId);
        if (houseSplit != null) {
            // 产权人
            result.put("holderPerson", houseSplit.getHolderPerson());
            DictItem houseStructure = dictService.getItemByGroupKeyAndValue(Constant.HOUSE_STRUCTURE_KEY, houseSplit.getStructure());
            // 房屋结构
            result.put("structure", houseStructure.getName());
            // 房屋用途
            result.put("purpose", houseSplit.getPurpose());
        }
        return JSON.toJSONString(result);
    }
}
