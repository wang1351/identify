/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : IdentifyController.java
 * 功能概要       :
 * 做成日期       : 2018-11-13・Administrator
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.controller;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.entity.IdentifyAccept;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.service.IdentifyAcceptService;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 鉴定业务申请Controller
 * @author panqh
 * @date 2018-11-13
 */
@Controller
@RequestMapping(path = "/tasks")
public class IdentifyController extends BaseViewController {

    @Autowired
    private IdentifyService identifyService;

    @Autowired
    private IdentifyAcceptService acceptService;

    @RequiresPermissions("task:view")
    @GetMapping
    public String toHtml() {
        boolean[] hasPermissions = SecurityUtils.getSubject().isPermitted("identify:request", "identify:accept", "identify:preview",
                                                                                  "identify:plan", "identify:contract", "identify:report",
                                                                                 "identify:verify", "identify:sign", "identify:send");

        boolean hasPermission = false;
        for (boolean b : hasPermissions) {
            if (b) {
                hasPermission = true;
                break;
            }
        }

        request().setAttribute("hasRequestPermission", hasPermission);

        boolean[] hasReviewPermissions = SecurityUtils.getSubject().isPermitted("identify:reviewRequest", "identify:reviewVerify", "identify:reviewExpert",
                                                                                        "identify:reviewConfirm", "identify:reviewOpinion", "identify:reviewSend");
        boolean hasReviewPermission = false;
        for (boolean b : hasReviewPermissions) {
            if (b) {
                hasReviewPermission = true;
                break;
            }
        }

        request().setAttribute("hasReviewPermission", hasReviewPermission);


        boolean[] hasCorrectPermissions = SecurityUtils.getSubject().isPermitted("identify:correctRequest", "identify:correctVerify", "identify:correctUpload");
        boolean hasCorrectPermission = false;
        for (boolean b : hasCorrectPermissions) {
            if (b) {
                hasCorrectPermission = true;
                break;
            }
        }
        request().setAttribute("hasCorrectPermission", hasCorrectPermission);

        return "myTaskList";
    }

    @PostMapping(path = "/search/page")
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Map<String, Object> condition = getQueryMsg(params);

        if (!StringUtil.isNullOrEmpty((String) condition.get("startTime"))) {
            condition.put("startTime", (String) condition.get("startTime") + " 00:00:00");
        }
        if (!StringUtil.isNullOrEmpty((String) condition.get("endTime"))) {
            condition.put("endTime", (String) condition.get("endTime") + " 23:59:59");
        }

        condition.put("userId", UserContextUtil.userId());
        condition.put("identifyOrg", UserContextUtil.orgId());
        condition.put("suspend",0);
        Page<IdentifyMain> target = identifyService.findPage(getQueryParameter(params), condition);
        return ResponseEntity.ok(target);
    }

    /**
     * 跳转至鉴定申请页面
     * @author panqh
     * @date 2018-11-14
     * @return
     */
    @RequiresPermissions("identify:request")
    @GetMapping(value = "/identifyRequest")
    public String toIdentifyRequestHtml() {
        return "identifyRequest";
    }

    @PostMapping(path = "/get/userInfo")
    @ResponseBody
    public ResponseEntity getUserInfo() {
        JSONObject userInfo = new JSONObject();
        userInfo.put("userId", UserContextUtil.userId());
        userInfo.put("userName", UserContextUtil.fullName());
        userInfo.put("orgId", UserContextUtil.orgId());
        userInfo.put("orgName", UserContextUtil.org().getFullName());
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping(path = "/identifyContent")
    @ResponseBody
    public ResponseEntity findIdentifyContent() {

        return ResponseEntity.ok();
    }

    @RequiresPermissions("identify:request")
    @PostMapping(path = "/save/identifyRequest")
    @ResponseBody
    public ResponseEntity saveIdentifyRequest(@RequestBody Map<String, Object> params) {
        UserContextUtil.addCurrentUser(params);

        identifyService.saveIdentifyRequestInfo(params);

        return ResponseEntity.ok();
    }

    @PostMapping(path = "/{mainId}/process")
    @ResponseBody
    public ResponseEntity stopSuspendOrResumeProcess(@PathVariable("mainId") String mainId, @RequestBody Map<String, Object> params) {
        params.put("mainId", mainId);
        UserContextUtil.addCurrentUser(params);
        identifyService.stopSuspendOrResumeProcess(params);
        return ResponseEntity.ok();
    }

    /**
     * 显示鉴定业务中每个分页的数量
     * @author wamgwj
     * @date 2018-12-12
     */
    @PostMapping(path = "/export/data")
    @ResponseBody
    public ResponseEntity findListData(){

        Map<String,Object> condition =new HashMap<>();
        condition.put("userId", UserContextUtil.userId());

        condition.put("identifyOrg", UserContextUtil.orgId());


        List<IdentifyMain> list = identifyService.findList(condition);
        return ResponseEntity.ok(list);
    }

    /**
     * 判断当前任务是否即将超期
     */
    @PostMapping(path = "/{id}/overdue")
    @ResponseBody
    public ResponseEntity isOverdue(@PathVariable("id") String mainId) {
        IdentifyAccept accept = acceptService.getAcceptByMainId(mainId);

        if (accept == null) {
            return ResponseEntity.ok(0);
        }

        Date requireDate = accept.getRequireDate();
        long mills = requireDate.getTime() - new Date().getTime();

        //相差的天数
        long day = mills / (1000 * 3600 * 24);

        if (day >= 0 && day <= 5) {
            return ResponseEntity.ok(1);
        }

        return ResponseEntity.ok(0);
    }


}
