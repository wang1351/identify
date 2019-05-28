/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ReviewController.java
 * 功能概要       :
 * 做成日期       : 2018-11-27・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.CollectionUtil;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.service.ExpertService;
import com.thinvent.nj.identify.service.ReviewService;
import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.entity.Org;
import com.thinvent.nj.uc.service.DictService;
import com.thinvent.nj.uc.service.OrgService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 复核业务实体 Controller
* @author panqh
* @date 2018-11-27
*/
@Controller
@RequestMapping(path = "/tasks/review")
public class ReviewController extends BaseViewController {

    @Autowired
    private DictService dictService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    private ReviewService reviewService;





    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Map<String, Object> condition = getQueryMsg(params);

        condition.put("curUserId", UserContextUtil.userId());

        condition.put("identifyOrg", UserContextUtil.orgId());


        Page<Review> target = reviewService.findPage(getQueryParameter(params), condition);
        return ResponseEntity.ok(target);
    }

    /*******************************  复核申请开始  ************************************/
    /**
     * 跳转至复核申请页面
     * @author panqh
     * @date 2018-11-27
     * @return
     */
    @RequiresPermissions("identify:reviewRequest")
    @RequestMapping(value = "/reviewRequest", method = RequestMethod.GET)
    public String toReviewRequestHtml() {
        return "reviewList";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(@PathVariable("id") String id) {
        return ResponseEntity.ok(reviewService.get(id));
    }


    /**
     * 保存复核申请信息
     * @author panqh
     * @date 2018-11-28
     * @param id
     * @param params
     * @return
     */
    @RequestMapping(path = "/{mainId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity saveReviewRequest(@PathVariable("mainId") String mainId, @RequestBody Map<String, Object> params) {

        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        reviewService.saveReviewRequestInfo(mainId, params);

        return ResponseEntity.ok();
    }
    /*******************************  复核申请结束  ************************************/

    /*******************************  复核受理开始  ************************************/
    /**
     * @author : xujc
     * @date :2018/12/25
     * @Description : 跳转至 复核受理页面
     */
    @RequiresPermissions("identify:reviewVerify")
    @RequestMapping(value = "/applyCheck", method = RequestMethod.GET)
    public String toApplyCheckHtml() {
        request().setAttribute("reviewId", request().getParameter("reviewId"));
        return "reviewAcceptList";
    }
    /**
     * @author : xujc
     * @date :2018/12/25
     * @Description : 保存复核受理信息
     */
    @RequiresPermissions("identify:reviewVerify")
    @RequestMapping(path = "/applyCheck/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveReviewApplyCheck(@RequestBody Map<String, Object> params) {

        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        reviewService.saveReviewApplyCheckInfo(params);

        return ResponseEntity.ok();
    }
    /*******************************  复核受理结束  ************************************/

    /*******************************  选择专家开始  ************************************/
    /**
     * 跳转至复核选择专家页面
     * @author panqh
     * @date 2018-11-28
     * @return
     */

    @RequiresPermissions("identify:reviewExpert")
    @RequestMapping(value = "/selectExpert", method = RequestMethod.GET)
    public String toSelectExpertHtml() {
        request().setAttribute("reviewId", request().getParameter("reviewId"));
        return "reviewExpertList";
    }

    /**
     * 获取专家树
     * @author panqh
     * @date 2018-11-29
     * @param reviewId
     * @return
     */
    @RequestMapping(path = "/{reviewId}/tree", method = RequestMethod.POST)
    public void getExpertTree(@PathVariable("reviewId") String reviewId,String type) {
        List<Map<String, Object>> targets = new ArrayList<>();

        Map<String, Object> target1 = null;
        Map<String, Object> target2 = null;

        List<DictItem> lstItem = dictService.getItemsByGroupKey(Constant.EXPERT_FIELD_KEY);

        for (DictItem item : lstItem) {
            target1 = new HashMap<>();
            target1.put("pId", "");
            ExpertAppoint expertAppoint = reviewService.getExpertAppointByReviewIdAndField(reviewId, String.valueOf(item.getValue()));
            target1.put("id", item.getValue());
            if (expertAppoint != null) {
                target1.put("name", item.getName() + (type == null?("【本次需选出 " + expertAppoint.getExpertNo() + " 个】"):""));
            } else {
                target1.put("name", item.getName());
            }
            targets.add(target1);

            List<ExpertBusinessArea> lstExpertField = reviewService.getExpertList(reviewId, item.getValue());
            for (ExpertBusinessArea expertField : lstExpertField) {
                target2 = new HashMap<>();
                target2.put("pId", item.getValue());
                target2.put("id", expertField.getExpertId());
                Expert expert = expertService.get(expertField.getExpertId());
                target2.put("name", expert.getName() + "（" + (expert.getTitle() ==null?"":expert.getTitle()) + " " + expert.getPhone() + "）");
                targets.add(target2);
            }
        }
        render(CollectionUtil.listToZtree(targets, "id", "pId", "name"));
    }

    /**
     * 随机选择专家
     * @author panqh
     * @date 2018-12-03
     * @param params
     * @return
     */
    @RequestMapping(path = "/expert/random", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity randomExpert(@RequestBody Map<String, Object> params) {
        Map<Integer, List<String>> result = reviewService.getRandomExpertListByCondition(params);
        return ResponseEntity.ok(JSON.toJSON(result));
    }

    /**
     * 验证复核选择专家信息
     * @author panqh
     * @date 2018-12-03
     * @param params
     * @return
     */
    @RequestMapping(path = "/expert/check", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity checkExpertInfo(@RequestBody Map<String, Object> params) {

        String result = reviewService.validatorReviewExpert(params);

        return ResponseEntity.ok(JSON.parseObject(result));
    }

    /**
     * @author : xujc
     * @date :2018/12/28
     * @Description : 将随机获取的专家找到
     */
    @RequestMapping(path = "/expert/getAutoChecked", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getAutoChecked(@RequestBody  Map<String, Object> params) {
        String str = (String) params.get("str");
        List<Map<String, Object>> autoChecked = reviewService.getAutoChecked(str);

        return ResponseEntity.ok(autoChecked);
    }

    /**
     * @author : xujc
     * @date :2018/12/28
     * @Description : 根据复核id获取指定专家信息
     */
    @RequestMapping(path = "/expert/getExpertAppoints", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getExpertAppoints(@RequestBody  Map<String, Object> params) {
        List<ExpertAppoint> expertAppoints = reviewService.getExpertAppointsByReviewId(params);
        return ResponseEntity.ok(expertAppoints);
    }

    /**
     * 保存复核选择专家信息
     * @author panqh
     * @date 2018-12-03
     * @param params
     * @return
     */
    @RequestMapping(path = "/expert/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveReviewExpert(@RequestBody Map<String, Object> params) {

        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        reviewService.saveReviewExpertInfo(params);

        return ResponseEntity.ok();
    }
    /*******************************  选择专家结束  ************************************/
    /*******************************  确认专家开始  ************************************/
    /**
     * @author : xujc
     * @date :2018/12/28
     * @Description : 跳转确认专家页面
     */
    @RequiresPermissions("identify:reviewConfirm")
    @RequestMapping(value = "/confirmExpert", method = RequestMethod.GET)
    public String toConfirmExpertHtml() {
        request().setAttribute("reviewId", request().getParameter("reviewId"));
        return "confirmExpertList";
    }

    @RequestMapping(path = "/expert/confirm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveReviewConfirmExpert(@RequestBody Map<String, Object> params) {

        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        reviewService.saveReviewConfirmExpertInfo(params);

        return ResponseEntity.ok();
    }
    /**
     * @author : xujc
     * @date :2018/12/28
     * @Description : 获取选择专家信息
     */
    @ResponseBody
    @RequestMapping(value = "/expert/getReviewExperts", method = RequestMethod.POST)
    public ResponseEntity getReviewExperts(@RequestBody Map<String,Object> map){
        List<Map<String, Object>> list = reviewService.getReviewExpertsByCondition(map);
        return ResponseEntity.ok(list);
    }


    /**
     * 跳转至复核填写意见页面
     * @author panqh
     * @date 2018-12-04
     * @return
     */
    @RequiresPermissions("identify:reviewOpinion")
    @RequestMapping(value = "/writeOpinion", method = RequestMethod.GET)
    public String toOpinionHtml() {
        request().setAttribute("reviewId", request().getParameter("reviewId"));
        return "reviewOpinionList";
    }

    /**
     * 保存复核填写意见信息
     * @author panqh
     * @date 2018-12-04
     * @param params
     * @return
     */
    @RequestMapping(path = "/opinion/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveReviewOpinion(@RequestBody Map<String, Object> params) {

        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        reviewService.saveReviewOpinionInfo(params);

        return ResponseEntity.ok();
    }

    @RequiresPermissions("identify:reviewSend")
    @RequestMapping(path = "/send/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveReviewSend(@RequestBody Map<String, Object> params) {
        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        reviewService.saveReviewSendInfo(params);

        return ResponseEntity.ok();
    }

    /**
     * 显示复核业务中每个分页的数量
     * @author wamgwj
     * @date 2018-12-12
     * @param
     * @throws Exception
     */

    @RequestMapping(path = "/search/data", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findListData() {
        Map<String, Object> condition = new HashMap<>();
        condition.put("curUserId", UserContextUtil.userId());
        condition.put("identifyOrg", UserContextUtil.orgId());
         List<Review>list=reviewService.findList(condition);
        return ResponseEntity.ok(list);
    }
    /**
     * @author : xujc
     * @date :2018/12/14
     * @Description : 根据领域查询可用专家数
     */
    @RequestMapping(path = "/getExpertByField",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getExpertByField(@RequestBody Map<String,Object> map){
        List<String> expertIds = reviewService.getExpertsByField((Integer) map.get("field"));
        return ResponseEntity.ok(expertIds);
    }

}