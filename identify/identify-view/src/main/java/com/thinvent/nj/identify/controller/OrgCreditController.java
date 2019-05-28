package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.entity.CreditGrade;
import com.thinvent.nj.identify.entity.OrgCredit;
import com.thinvent.nj.identify.entity.OrgCreditScore;
import com.thinvent.nj.identify.service.OrgCreditService;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* 企业信用表 Controller
* @author xujc
* @date 2018-11-12
*/
@Controller
@RequestMapping(path = "/enterprise/credits")
public class OrgCreditController extends BaseViewController {

    @Autowired
    private OrgCreditService orgCreditService;
    @RequiresPermissions("orgCredit:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        return "orgCreditList";
    }
    /**
     * @author : xujc
     * @date :2018/11/13
     * @Description : 跳转评分页面
     * @param : id 鉴定机构id
     * @param : type 1对当前年进行评分，2查看历史评分
     *
     */
    @RequiresPermissions("orgCredit:view")
    @RequestMapping(value = "/toScore", method = RequestMethod.GET)
    public String toScoreHtml() {
        // update by xujc 2019/1/14 start
        String id = request().getParameter("orgId");
        String type = request().getParameter("type");
        String tab = request().getParameter("tab");
        List<OrgCredit> orgCredits = orgCreditService.getListByOrgIdAndType(id, type);
        // 向叶面传个企业信用对象
        request().setAttribute("orgCredit",orgCredits.get(0));
        // 将是评分还是查看详细传到页面
        request().setAttribute("type",type);
        request().setAttribute("tab",tab);
        // update by xujc 2019/1/14 end
        return "orgCreditScore";
    }


    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Page<OrgCredit> target = orgCreditService.findPage(getQueryParameter(params), getQueryMsg(params));
        return ResponseEntity.ok(target);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(@PathVariable("id") String id) {
        return ResponseEntity.ok(orgCreditService.get(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        OrgCredit target = new OrgCredit();
        MapperUtil.copy(params, target);

        orgCreditService.insert(target);

        return ResponseEntity.ok();
    }


    //update by wangwj 20181226 start
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) {
        OrgCredit target = orgCreditService.get(id);
        MapperUtil.copy(params, target);

        orgCreditService.update(target);

        return ResponseEntity.ok();
    }

    //update by wangwj 20181226 end

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity del(@PathVariable("id") String id) {
        orgCreditService.delete(id);
        return ResponseEntity.ok();
    }

    /**
     * @author : xujc
     * @date :2018/11/14
     * @Description : 保存操作包括添加和修改
     * @param : orgCreditId 用来判断是添加或修改，""代表添加，非空代表修改
     * @param : identifyOrgId 机构id
     */
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity save( @RequestBody Map<String,Object> map) {
        orgCreditService.save(map);
        return ResponseEntity.ok();
    }
    /**
     * @author : xujc
     * @date :2018/11/16
     * @Description : 根据信用id 和评分类型获取评分对象集合
     * @param map ->orgCreditId\orgScoreType
     *
     */
    @RequestMapping(path = "/getByIdAndType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getByIdAndType(@RequestBody Map<String,Object> map) {
        String orgCreditId = (String) map.get("orgCreditId");
        String orgScoreType = (String) map.get("orgScoreType");
        List<OrgCreditScore> OrgCreditScores = orgCreditService.getByOrgCreditIdAndOrgScoreType(orgCreditId, Integer.parseInt(orgScoreType));
        return ResponseEntity.ok(OrgCreditScores);
    }

    /**
     * @author : xujc
     * @date :2018/11/16
     * @Description : 根据机构id 年份 评分类型获取评分对象集合
     * @param map -> identifyOrgId\year\orgScoreType\time
     */
    @RequestMapping(path = "/getByYear", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getByYear(@RequestBody Map<String,Object> map) {

        List<OrgCreditScore> OrgCreditScores = orgCreditService.getOrgCreditScoreList(map);
        return ResponseEntity.ok(OrgCreditScores);
    }
    /**
     * @author : xujc
     * @date :2018/11/29
     * @Description : 信息回填标题用
     */
    @RequestMapping(path = "/getOrgCredit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getOrgCredit(@RequestBody Map<String,Object> map) {
        List<OrgCredit> orgCredits = orgCreditService.getListByOrgIdAndType( (String)map.get("orgId"), "1");
        return ResponseEntity.ok(orgCredits.get(0));
    }

    // 根据评分找等级
    @RequestMapping(path = "/getByScore", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getByScore(@RequestBody Map<String,Object> map) {
        CreditGrade creditGrade = orgCreditService.getByScore((Integer) map.get("score"));
        return ResponseEntity.ok(creditGrade);
    }





}
