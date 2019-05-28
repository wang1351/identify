package com.thinvent.nj.identify.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.common.util.SpringContextUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.IdentifyAccept;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.entity.Message;
import com.thinvent.nj.identify.enums.RecordStatus;
import com.thinvent.nj.identify.handler.AbstractBusinessHandler;
import com.thinvent.nj.identify.mapper.IdentifyAcceptMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AcceptHandler extends AbstractBusinessHandler {

    private IdentifyAcceptMapper identifyAcceptMapper = SpringContextUtil.getBean(IdentifyAcceptMapper.class);

    private String acceptId;

    public AcceptHandler(Map<String, Object> params) {
        super(params);
        mainId = (String) params.get("mainId");
    }

    @Override
    protected void save() {

        // 保存受理表
        saveAccept();

        // 更新鉴定主表
        updateIdentifyMain();
    }


    private void updateIdentifyMain() {
        IdentifyMain identifyMain = identifyService.get(mainId);
        String accept = (String) params.get("accept");

        if ("1".equals(accept)) {
            // 更新主表状态 -> 已受理
            identifyMain.setStatus(Constant.IDENTIFY_STATUS_SL);
        } else {
            // 跟新主表状态 -> 未受理
            identifyMain.setStatus(Constant.IDENTIFY_STATUS_WSL);
        }

        identifyMain.setOperatorTime(new Date());
        identifyService.update(identifyMain);
    }

    private void saveAccept() {
        // 受理表
        IdentifyAccept identifyAccept = new IdentifyAccept();
        MapperUtil.copy(params, identifyAccept);

        JSONArray joinUserIdArray = (JSONArray) params.get("joinUserId");

        if (joinUserIdArray != null && joinUserIdArray.size() > 0) {
            String joinUserIds = String.join(",", (List<String>)(List)joinUserIdArray);
            identifyAccept.setJoinPerson(joinUserIds);
        }

        identifyAccept.setCreateUsername(curFullName);
        identifyAccept.setCreateUser(curUserId);
        identifyAcceptMapper.insert(identifyAccept);

        // 要求完成时间
        Date requireDate = identifyAccept.getRequireDate();
        // update by xujc 2019/1/27 start
        // 不受理时不传requireDate
        if(requireDate != null){
            long mills = requireDate.getTime() - new Date().getTime();

            //相差的天数
            long day = mills / (1000 * 3600 * 24);

            if (day >= 0 && day <= 5) {
                IdentifyMain identifyMain = identifyService.get(mainId);
                String caseNo = identifyMain.getCaseNo();
// update by xujc 2019/2/28 start
                Message message = new Message();
                message.setTitle("您有一个安全鉴定待办任务即将过期");
                message.setContent("安全鉴定编号【" + caseNo + "】的任务即将到期，请及时进行处理");
                message.setMsgType(1);
                message.setReceiveUserId((String)params.get("chargeId"));
// update by xujc 2019/2/28 end
                messageService.insert(message);
            }
        }
        // update by xujc 2019/1/27 end

        acceptId = identifyAccept.getId();
    }

    @Override
    protected void record() {
        String accept = (String) params.get("accept");

        RecordStatus status;
        String remarks = null;
        if ("1".equals(accept)) {
            status = RecordStatus.ACCEPT;
        } else {
            status = RecordStatus.UN_ACCEPT;
            remarks = (String) params.get("remarks");
        }

        internelRecord(status, acceptId, remarks);
    }

    @Override
    protected void process() {
        String accept = (String) params.get("accept");
        Map<String, Object> variables = new HashMap<>(2);
        if ("0".equals(accept)) {
            variables.put("status", 10);
        } else {
            variables.put("status", 2);
        }

        variables.put("previewUserId", params.get("chargeId"));

        completeTask(variables);
    }





}
