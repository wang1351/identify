/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : IdentifyPhoneController.java
 * 功能概要       :
 * 做成日期       : 2019-05-14
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.controller;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.entity.Correct;
import com.thinvent.nj.identify.entity.IdentifyAccept;
import com.thinvent.nj.identify.entity.IdentifyApply;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.service.IdentifyAcceptService;
import com.thinvent.nj.identify.service.IdentifyApplyService;
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
 * @author : xujc
 * @date :2019/5/14
 * @Description : IdentifyPhoneController 手机申请Controller
 */

@Controller
@RequestMapping(path = "/phone")
public class IdentifyPhoneController extends BaseViewController {

    @Autowired
    private IdentifyApplyService applyService;

    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Map<String, Object> condition = getQueryMsg(params);


        Page<IdentifyApply> target = applyService.findPage(getQueryParameter(params), condition);
        return ResponseEntity.ok(target);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(@PathVariable("id") String id) {
        return ResponseEntity.ok(applyService.get(id));
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") String id) throws Exception {
        applyService.refuseApply(id);
        return ResponseEntity.ok();
    }
    /**
     * @author : xujc
     * @date :2019/5/14
     * @Description : 获取总任务数
     */
    @RequestMapping(path = "/search/data", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findListData() {
        Map<String, Object> condition = new HashMap<>();

        condition.put("curUserId", UserContextUtil.userId());

        condition.put("identifyOrg", UserContextUtil.orgId());


        List<IdentifyApply> list = applyService.findList(condition);

        return ResponseEntity.ok(list);
    }

}
