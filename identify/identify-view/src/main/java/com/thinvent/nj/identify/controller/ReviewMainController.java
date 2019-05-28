/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewMainController.java
 * 功能概要       :
 * 做成日期       : 2018-12-05・xujc
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.service.ReviewMainService;
import com.thinvent.nj.identify.service.ReviewRecordService;
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
@RequestMapping(path = "/reviewMain")
public class ReviewMainController extends BaseViewController {
    @Autowired
    private ReviewMainService reviewMainService;
    @Autowired
    private ReviewRecordService reviewRecordService;
    @Autowired
    private OrgService orgService;

    @RequiresPermissions("reviewList:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        // 当前用户的机构对象
        Org org = orgService.get(UserContextUtil.orgId());
        // 鉴定处对象
        Org jdOrg = orgService.getByCode(Constant.IDENTIFY_DEP_CODE);
        if(org.getPId().equals(jdOrg.getId())){
            request().setAttribute("isJdc",1);
        }
        return "reviewMainList";
    }


    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Map<String, Object> condition = getQueryMsg(params);
        String status =(String)condition.get("status");
        // 当前用户的机构对象
        Org org = orgService.get(UserContextUtil.orgId());
        // 鉴定处对象
        Org jdOrg = orgService.getByCode(Constant.IDENTIFY_DEP_CODE);
        // 如果当前机构对象的父id等于鉴定处的id
        if(org.getPId().equals(jdOrg.getId())){
            condition.put("orgSearch",org.getId());
        }
        Page<Review> target = reviewMainService.findPage(getQueryParameter(params), condition);
        return ResponseEntity.ok(target);
    }

    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    public String detailPage() {
        return "reviewDetail";
    }
    /**
     * @author : xujc
     * @date :2018/12/4
     * @Description : 获取复核申请信息
     * @param : id : 是复核表的id
     */
    @RequestMapping(path = "/getApply/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getApply(@PathVariable String id){
        Review review = reviewMainService.getById(id);
        return ResponseEntity.ok(review);
    }
    @RequestMapping(path = "/getAccept/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getAccept(@PathVariable String id){
        ReviewAccept reviewAccept = reviewMainService.getByReviewId(id);
        return ResponseEntity.ok(reviewAccept);
    }

    /**
     * @author : xujc
     * @date :2018/12/4
     * @Description : 获取选择专家对象
     * @param : reviewId : 是复核表的id;type 1：选择专家 2：确认专家
     */
    @RequestMapping(path = "/getReviewExpert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getReviewExpert(@RequestBody Map<String,Object> params){
        List<Map<String, Object>> reviewExperts = reviewMainService.getReviewExpertsByCondition(params);
        return ResponseEntity.ok(reviewExperts);
    }

    @RequestMapping(path = "/getReviewOpinion", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getReviewOpinion(@RequestBody Map<String,Object> params){
        ReviewOpinion reviewOpinion = reviewMainService.getReviewOpinionByReviewId(params);
        return ResponseEntity.ok(reviewOpinion);
    }


    /**
     * @author : xujc
     * @date :2018/12/4
     * @Description : 获取复核发放对象
     * @param : id : 是复核表的id
     */
    @RequestMapping(path = "/getReviewSend/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getReviewSend(@PathVariable String id){
        ReviewSend reviewSend = reviewMainService.getReviewSendByReviewId(id);
        return ResponseEntity.ok(reviewSend);
    }
    /**
     * @author : xujc
     * @date :2018/12/13
     * @Description : 根据mainId找到review
     */
   /* @RequestMapping(path = "/getReviewByMainId/{mainId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getReviewByMainId(@PathVariable String mainId){
        Review review = reviewMainService.getByMainId(mainId);
        return ResponseEntity.ok(review);
    }*/
    /**
     * @author : xujc
     * @date :2019/1/3
     * @Description : 根据reviewId得到记录表集合
     */
    @RequestMapping("/getReviewRe")
    @ResponseBody
    public ResponseEntity getRecoudByMainId(@RequestBody Map<String,Object> map){
        List<IdentifyReviewRecord> list = reviewRecordService.getListByCondition(map);
        return ResponseEntity.ok(list);
    }

}
