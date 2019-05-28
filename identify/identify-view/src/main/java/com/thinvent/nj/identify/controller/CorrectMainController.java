/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectMainController.java
 * 功能概要       :
 * 做成日期       : 2018-12-20・xujc
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.Correct;
import com.thinvent.nj.identify.entity.CorrectVerify;
import com.thinvent.nj.identify.entity.IdentifyCorrectRecord;
import com.thinvent.nj.identify.service.CorrectMainService;
import com.thinvent.nj.identify.service.CorrectRecordService;
import com.thinvent.nj.identify.service.CorrectService;
import com.thinvent.nj.uc.entity.Org;
import com.thinvent.nj.uc.service.OrgService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* 复核业务实体 Controller
* @author panqh
* @date 2018-11-27
*/
@Controller
@RequestMapping(path = "/correctMain")
public class CorrectMainController extends BaseViewController {
    @Autowired
    private CorrectMainService correctMainService;
    @Autowired
    private CorrectRecordService correctRecordService;
    @Autowired
    private CorrectService correctService;
    @Autowired
    private OrgService orgService;

    // update by xujc 2019/3/12 start
    @RequiresPermissions("correctList:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml(){
        // 当前用户的机构对象
        Org org = orgService.get(UserContextUtil.orgId());
        // 鉴定处对象
        Org jdOrg = orgService.getByCode(Constant.IDENTIFY_DEP_CODE);
        if(org.getPId().equals(jdOrg.getId()) ){
            request().setAttribute("isJdc",1);
        }
        return "correctMainList";
    }
    // update by xujc 2019/3/12 end
    // update by xujc 2019/1/16 start
    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Map<String, Object> condition = getQueryMsg(params);
        String status = (String)condition.get("status");
        // 当前用户的机构对象
        Org org = orgService.get(UserContextUtil.orgId());
        // 鉴定处对象
        Org jdOrg = orgService.getByCode(Constant.IDENTIFY_DEP_CODE);
        // 如果当前机构对象的父id等于鉴定处的id
        if(org.getPId().equals(jdOrg.getId())){
            condition.put("orgSearch",org.getId());
        }
        // update by xujc 2019/1/16 end
        Page<Correct> target = correctMainService.findPage(getQueryParameter(params), condition);
        return ResponseEntity.ok(target);
    }

    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    public String detailPage() {
        return "correctDetail";
    }

    /**
     * @author : xujc
     * @date :2018/12/4
     * @Description : 获取补正申请信息以及附件信息
     * @param : id : 是补正表的id
     */
    @RequestMapping(path = "/getApply/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getApply(@PathVariable String id){
        Correct correct = correctMainService.getById(id);
        return ResponseEntity.ok(correct);
    }
    /**
     * @author : xujc
     * @date :2018/12/20
     * @Description : 获取补正审核信息
     */
    @RequestMapping(path = "/getCheck/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getCheck(@PathVariable String id){
        CorrectVerify correctVerify = correctMainService.getCheckByCorrectId(id);
        return ResponseEntity.ok(correctVerify);
    }


    /**
     * @author : xujc
     * @date :2018/12/18
     * @Description : 根据补正主表id获取recoud对象集合
     */
    @RequestMapping("/getRecoud")
    @ResponseBody
    public ResponseEntity getRecoudByCorrectId(@RequestBody Map<String,Object> map){
        List<IdentifyCorrectRecord> list = correctRecordService.getListByCondition(map);
        return ResponseEntity.ok(list);
    }

}
