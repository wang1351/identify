/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : identifyServiceImplTest.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・Administrator
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.mybatis.service.ProcessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiTest {

    @Resource(name = "mainProcessService")
    private ProcessService mainService;


    // 鉴定委托员
    private static final String USER_REQUEST = "7A8461010D88F98FE05010AC0C052FC6";

    // 受理员
    private static final String USER_ACCEPT = "7A8461010D89F98FE05010AC0C052FC6";

    // 初勘员
    private static final String USER_PREVIEW = "7A8461010D8AF98FE05010AC0C052FC6";

    // 出具方案员
    private static final String USER_PLAN = "7A8461010D8BF98FE05010AC0C052FC6";

    // 签订合同员
    private static final String USER_CONTRACT = "7A8461010D8DF98FE05010AC0C052FC6";

    // 报告编制员
    private static final String USER_REPORT = "7A8461010D8EF98FE05010AC0C052FC6";

    // 报告审核员
    private static final String USER_VERIFY = "7A8461010D8FF98FE05010AC0C052FC6";

    // 报告签发员
    private static final String USER_SIGN = USER_VERIFY;

    // 报告发送员
    private static final String USER_SEND = USER_VERIFY;

    private Map<String, Object> variables;

    private boolean stop;

    @Test
    public void testMain() {
        start("1111", USER_REQUEST);

        // 受理
        variables = new HashMap<>();
        variables.put("status", 2);
        variables.put("previewUserId", USER_PREVIEW);
        doTask(USER_ACCEPT, variables);

        stop = true;

        if (stop) {
            return;
        }
        // 初勘
        doTask(USER_PREVIEW, variables);

        // 出具方案
        doTask(USER_PLAN, variables);

        // 签订合同
        doTask(USER_CONTRACT, variables);

        // 编制报告
        doTask(USER_REPORT, variables);

        // 报告审核
        doTask(USER_VERIFY, variables);

        // 签发
        doTask(USER_SIGN, variables);

        // 发放
        doTask(USER_SEND, variables);
    }


    private void doTask(String userId, Map<String, Object> variables) {
        String taskId = getTask(userId);
        claim(taskId, userId);

        if (variables == null) {
            variables = new HashMap<>();
        }
        completeTask(taskId, variables);
    }


    /**
     * 开启一个任务
     */
    private void start(String businessKey, String startUserId) {
        // start
        Map<String, Object> param = new HashMap<>(2);
        param.put("businessKey", businessKey);
        param.put("startUserId", startUserId);

        mainService.start(param);
    }

    /**
     * 获取我的任务
     * @param userId
     * @return
     */
    private String getTask(String userId) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("userId", userId);
        String tasks = mainService.getMyTasks(param);
        Map<String, Object> map = MapperUtil.convertToMap(tasks);

        System.out.println("userId : " + userId + ", tasks: " + map);

        String taskId = ((Map)map.get("data")).values().iterator().next().toString();

        return taskId;
    }

    /**
     * 签收任务
     * @param taskId
     * @param userId
     */
    private void claim(String taskId, String userId) {
        mainService.claim(taskId, userId);
    }

    /**
     * 反签收任务
     * @param taskId
     */
    private void unClaim(String taskId) {
        mainService.unClaim(taskId);
    }

    private void completeTask(String taskId, Map<String, Object> variables) {
        mainService.complete(taskId, variables);
    }

}
