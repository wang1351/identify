/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : CorrectController.java
 * 功能概要       :
 * 做成日期       : 2018-12-05・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.entity.Correct;
import com.thinvent.nj.identify.service.CorrectService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 补正业务表 Controller
 *
 * @author panqh
 * @date 2018-12-05
 */
@Controller
@RequestMapping(path = "/corrects")
public class CorrectController extends BaseViewController {

    @Autowired
    private CorrectService correctService;


    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Map<String, Object> condition = getQueryMsg(params);

        condition.put("curUserId", UserContextUtil.userId());

        condition.put("identifyOrg", UserContextUtil.orgId());

        Page<Correct> target = correctService.findPage(getQueryParameter(params), condition);

        return ResponseEntity.ok(target);
    }

    /**
     * 跳转至补正变更申请页面
     *
     * @return
     * @author panqh
     * @date 2018-11-27
     */
    @RequiresPermissions("identify:correctRequest")
    @RequestMapping(value = "/correctRequest", method = RequestMethod.GET)
    public String toReviewRequestHtml() {
        return "correctList";
    }

    /**
     * 保存补正申请信息
     *
     * @param id：鉴定列表主键
     * @param params
     * @return
     * @author panqh
     * @date 2018-12-05
     */
    @RequestMapping(path = "/{mainId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity saveCorrectRequest(@PathVariable("mainId") String mainId, @RequestBody Map<String, Object> params) {

        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        correctService.saveCorrectRequestInfo(mainId, params);

        return ResponseEntity.ok();
    }

    /**
     * 跳转至补正审核页面
     *
     * @return
     * @author panqh
     * @date 2018-12-05
     */
    @RequiresPermissions("identify:correctVerify")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String toCheckHtml() {
        request().setAttribute("correctId", request().getParameter("correctId"));
        return "correctCheckList";
    }

    /**
     * 保存补正审核信息
     *
     * @param params
     * @return
     * @author panqh
     * @date 2018-12-05
     */
    @RequestMapping(path = "/check/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveCorrectCheck(@RequestBody Map<String, Object> params) {

        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        correctService.saveCorrectCheckInfo(params);

        return ResponseEntity.ok();
    }

    /**
     * 跳转至补正上传资料页面
     *
     * @return
     * @author panqh
     * @date 2018-12-05
     */
    @RequiresPermissions("identify:correctUpload")
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String toUploadHtml() {
        request().setAttribute("correctId", request().getParameter("correctId"));
        return "correctUploadList";
    }

    /**
     * 保存补正上传资料信息
     *
     * @param params
     * @return
     * @author panqh
     * @date 2018-12-05
     */
    @RequestMapping(path = "/upload/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveCorrectUpload(@RequestBody Map<String, Object> params) {

        params.put("curUserId", UserContextUtil.userId());
        params.put("curUserName", UserContextUtil.fullName());

        correctService.saveCorrectUploadInfo(params);

        return ResponseEntity.ok();
    }

    /**
     * 显示补正业务中每个分页的数量
     *
     * @param
     * @throws Exception
     * @author wamgwj
     * @date 2018-12-12
     */
    @RequestMapping(path = "/search/data", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findListData() {
        Map<String, Object> condition = new HashMap<>();

        condition.put("curUserId", UserContextUtil.userId());

        condition.put("identifyOrg", UserContextUtil.orgId());


        List<Correct> list = correctService.findList(condition);

        return ResponseEntity.ok(list);
    }

    //add by wangwj 20181220 start
    //该接口提供补正编号的详细
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(@PathVariable("id") String id) {
        return ResponseEntity.ok(correctService.get(id));
    }
    //add by wangwj 20181220 end
}
