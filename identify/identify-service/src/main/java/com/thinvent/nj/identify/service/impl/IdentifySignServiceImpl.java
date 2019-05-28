package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.mapper.*;
import com.thinvent.nj.identify.service.IdentifyRecordService;
import com.thinvent.nj.identify.service.IdentifyReportService;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.identify.service.IdentifySignService;
import com.thinvent.nj.mybatis.service.ProcessService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import com.thinvent.nj.supervise.service.SuperviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 鉴定报告签发服务实现
 *
 * @author liupj
 * @date 2018-11-21
 */
@Service
public class IdentifySignServiceImpl extends BaseCURDServiceImpl<IdentifySign, String> implements IdentifySignService {

    @Autowired
    private IdentifyMainMapper identifyMapper;

    @Autowired
    protected ClientMapper clientMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseSplitMapper houseSplitMapper;

    @Autowired
    private IdentifyReportDetailMapper reportDetailMapper;

    @Autowired
    private IdentifySignMapper identifySignMapper;

    @Resource(name = "mainProcessService")
    private ProcessService mainService;

    @Resource(name = "activeProcessService")
    private ProcessService activeService;

    @Autowired
    private IdentifyService identifyService;

    @Autowired
    private IdentifyRecordService identifyRecordService;

    @Autowired
    private SuperviseService superviseService;

    @Autowired
    private IdentifyReportService reportService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Map<String, Object> params) {
        String curFullName = (String)params.get("curFullName");
        String curUserId = (String)params.get("curUserId");
        String mainId = (String) params.get("mainId");
        String reportId = (String) params.get("reportId");

        // 更新主表状态 -> 已签发
        IdentifyMain identifyMain = identifyService.get(mainId);
        identifyMain.setStatus(Constant.IDENTIFY_STATUS_QF);
        identifyMain.setOperatorTime(new Date());
        identifyService.update(identifyMain);

        IdentifySign target = new IdentifySign();
        MapperUtil.copy(params, target);

        target.setCreateUsername(curFullName);
        target.setCreateUser(curUserId);
        target.setCreateTime(new Date());
        identifySignMapper.insert(target);

        // 流程记录表
        Map<String, Object> recordPrams = new HashMap<>(6);
        recordPrams.put("mainId", mainId);
        recordPrams.put("curFullName", curFullName);
        recordPrams.put("status", Constant.IDENTIFY_STATUS_QF);
        recordPrams.put("businessKey",target.getId());
        identifyRecordService.insert(recordPrams);

        // add by panqh 2018-12-20 start
        // 危房监管表
        try {
            List<Map<String, Object>> lstDangerHouse = getDangerHouseInfo(params);
            if (lstDangerHouse != null && lstDangerHouse.size() > 0) {
                for (int i = 0; i < lstDangerHouse.size(); i++) {
                    Map<String, Object> dangerHouse = lstDangerHouse.get(i);
                    superviseService.saveDangerHouseInfo(dangerHouse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add by panqh 2018-12-20 end

        // 完成任务
        IdentifyReport report = reportService.get(reportId);
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
     * 组装需要保存的危房信息
     * @author panqh
     * @date 2018-12-20
     * @param params
     * @return
     */
    private List<Map<String, Object>> getDangerHouseInfo(Map<String, Object> params) {
        String mainId = (String) params.get("mainId");
        String curUserId = (String) params.get("curUserId");
        String curUserName = (String) params.get("curFullName");
        // 鉴定主表实体
        IdentifyMain identify = identifyMapper.get(mainId);
        // 申请人信息
        Client client = clientMapper.getClientByMainId(mainId);
        // 房屋基本信息实体
        House houseFk = houseMapper.getHouseByMainId(mainId);
        House housePk = houseMapper.get(houseFk.getId());
        // 鉴定签发实体
        IdentifySign identifySign = identifySignMapper.getIdentifySignByMainId(mainId);
        String reportId = identifySign.getReportId();
        // 鉴定报告明细列表
        List<IdentifyReportDetail> lstReportDetail = reportDetailMapper.getByReportId(reportId);
        List<Map<String, Object>> lstMap = new ArrayList<>();
        Map<String, Object> target = null;
        if (lstReportDetail != null && lstReportDetail.size() > 0) {
            for (IdentifyReportDetail reportDetail : lstReportDetail) {
                if ("3".equals(reportDetail.getIdentifyResult()) || "4".equals(reportDetail.getIdentifyResult())) {
                    target = new HashMap<>();
                    // 当前登录人信息
                    target.put("curUserId", curUserId);
                    target.put("curUserName", curUserName);

                    // 鉴定主表信息
                    target.put("caseNo", identify.getCaseNo());
                    target.put("orgName", identify.getOrgName());

                    // 联系人姓名
                    target.put("contactName", client.getContactName());
                    // 联系电话
                    if (!StringUtil.isNullOrEmpty(client.getPhone()) && !StringUtil.isNullOrEmpty(client.getPhone2())) {
                        target.put("phone", client.getPhone() + ", " + client.getPhone2());
                    } else if (!StringUtil.isNullOrEmpty(client.getPhone()) && StringUtil.isNullOrEmpty(client.getPhone2())) {
                        target.put("phone", client.getPhone());
                    } else if (StringUtil.isNullOrEmpty(client.getPhone()) && !StringUtil.isNullOrEmpty(client.getPhone2())) {
                        target.put("phone", client.getPhone2());
                    } else {
                        target.put("phone", "");
                    }
                    //委托人
                    target.put("clientName", client.getClientName());
                    // 房屋基本信息
                    target.put("zoneValue", housePk.getZone());
                    target.put("streetValue", housePk.getStreet());
                    target.put("address", houseFk.getZone() + houseFk.getStreet() + houseFk.getAddress());

                    // 房屋分栋信息
                    HouseSplit houseSplit = houseSplitMapper.get(reportDetail.getHouseSplitId());
                    // 产权人
                    target.put("holderPerson", houseSplit.getHolderPerson());
                    // 房屋结构
                    target.put("structure", houseSplit.getStructure());
                    // 房屋用途
                    target.put("purpose", houseSplit.getPurpose());
                    //层数
                    target.put("layerAbove", houseSplit.getLayerAbove());
                    //建造年代
                    target.put("buildYear", houseSplit.getBuildYear());
                    // 鉴定报告明细信息
                    target.put("businessId", reportDetail.getId());
                    target.put("businessType", Constant.BUSINESS_TYPE_IDENTIFY);
                    target.put("result", reportDetail.getIdentifyResult());
                    target.put("conclusion", reportDetail.getConclusion());
                    target.put("opinion", reportDetail.getOpinion());

                    lstMap.add(target);
                }
            }
        }
        return lstMap;
    }

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据mainId获取鉴定报告签发信息.列表详细用
     *
     */
    @Override
    public IdentifySign getSignByMainId(String mainId) {
        return identifySignMapper.getIdentifySignByMainId(mainId);
    }
}
