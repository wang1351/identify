package com.thinvent.nj.identify.handler;

import com.thinvent.nj.common.util.SpringContextUtil;
import com.thinvent.nj.identify.enums.RecordStatus;
import com.thinvent.nj.identify.service.IdentifyMainService;
import com.thinvent.nj.identify.service.IdentifyRecordService;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.identify.service.MessageService;
import com.thinvent.nj.mybatis.service.ProcessService;

import java.util.HashMap;
import java.util.Map;

/**
 * 鉴定业务业务处理抽象类
 *
 * 鉴定业务处理具有通用性，即执行以下三步：
 *  <ul>
 *      <li>
 *          save: 保存业务表
 *      </li>
 *      <li>
 *          record: 保存操作记录表
 *      </li>
 *      <li>
 *          process: 执行流程扭转（发起或执行任务节点）
 *      </li>
 *  </ul>
 *
 *  所以本抽象类使用template method design pattern，对子类暴露save, record， process方法，本类中
 *  再依次调用以上方法。
 *
 *  采用该模式后能规范化业务功能
 *
 */
public abstract class AbstractBusinessHandler implements BusinessHandler {

    private IdentifyRecordService recordService = SpringContextUtil.getBean(IdentifyRecordService.class);


    protected IdentifyMainService identifyMainService = SpringContextUtil.getBean(IdentifyMainService.class);

    protected IdentifyService identifyService = SpringContextUtil.getBean(IdentifyService.class);

    protected MessageService messageService = SpringContextUtil.getBean(MessageService.class);

    protected ProcessService mainService = (ProcessService) SpringContextUtil.getBean("mainProcessService");

    protected Map<String, Object> params;

    protected String mainId;

    protected String curUserId;

    protected String curFullName;

    protected String curOrgId;

    public AbstractBusinessHandler(Map<String, Object> params) {
        this.params = params;
        this.curUserId = (String)params.get("curUserId");
        this.curFullName = (String)params.get("curFullName");
        this.curOrgId = (String)params.get("curOrgId");
    }

    protected abstract void save();

    protected abstract void record();

    protected abstract void process();

    @Override
    public void execute() {

        save();

        record();

        process();
    }

    // 流程记录表
    protected void internelRecord(RecordStatus recordStatus, String businessKey, String remarks) {
        Map<String, Object> recordPrams = new HashMap<>(6);
        recordPrams.put("mainId", mainId);
        recordPrams.put("curFullName", curFullName);
        recordPrams.put("status", recordStatus.getNum());
        recordPrams.put("businessKey", businessKey);
        recordPrams.put("remarks", remarks);
        recordService.insert(recordPrams);
    }

    // 流程记录表(overload)
    protected void internelRecord(RecordStatus recordStatus) {
        internelRecord(recordStatus, null, null);
    }

    // 启动流程
    protected void startProcess() {
        Map<String, Object> param = new HashMap<>(2);
        param.put("businessKey", mainId);
        param.put("startUserId", curUserId);
        mainService.start(param);
    }

    /**
     * 完成任务
     * @param variables
     */
    protected void completeTask(Map<String, Object> variables) {
        // 完成任务
        String taskId = mainService.getTaskId(curUserId, mainId);

        if (variables == null) {
            variables = new HashMap<>(0);
        }

        mainService.complete(taskId, variables);
    }
}
