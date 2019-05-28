package com.thinvent.nj.identify.controller;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.DateUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.HouseSplit;
import com.thinvent.nj.identify.entity.IdentifyMain;
import com.thinvent.nj.identify.entity.IdentifyReport;
import com.thinvent.nj.identify.service.IdentifyMainService;
import com.thinvent.nj.identify.service.IdentifyReportService;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.service.DictService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编制鉴定报告 Controller
 *
 * @author liupj
 * @date 2018-11-21
 */
@Controller
@RequestMapping(path = "/identifies/report")
public class IdentifyReportController extends BaseViewController {

    @Autowired
    private IdentifyReportService identifyReportService;

    @Autowired
    private IdentifyService identifyService;

    @Autowired
    private IdentifyMainService identifyMainService;

    @Autowired
    private DictService dictService;

    @RequiresPermissions("identifyList:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        // 激活流程跳转到鉴定页面
        String mainId = request().getParameter("mainId");
        request().setAttribute("mainId", mainId);
        IdentifyMain identify = identifyService.get(mainId);

        if (request().getParameter("isActive") != null) {
            request().setAttribute("isActive", Boolean.parseBoolean(request().getParameter("isActive")));
        } else {
            Integer unfinishedCount = identify.getUnfinishedCount();
            if (unfinishedCount != null) {
                request().setAttribute("isActive", true);
            }else {
                request().setAttribute("isActive", false);
            }
        }



        String content = identify.getContent();
        request().setAttribute("content", content);

        // claim
        identifyService.claim(mainId, UserContextUtil.userId());
        return "identifyReportList";
    }

    /**
     * 获取分栋信息
     *
     * @param params
     * @return
     */
    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        IdentifyMain identifyMain = identifyMainService.get((String) getQueryMsg(params).get("mainId"));

        List<HouseSplit> houseSplits = identifyMain.getHouse().getHouseSplitList();

        JSONObject jsonObject = new JSONObject(1);
        jsonObject.put("result", houseSplits);

        return ResponseEntity.ok(jsonObject);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        params.put("curUserId", UserContextUtil.userId());
        params.put("curFullName", UserContextUtil.fullName());

        identifyReportService.insert(params);

        return ResponseEntity.ok();
    }

    @RequestMapping(path = "/qrInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getQrInfo(@RequestBody Map<String, Object> params) {
        String mainId = (String) params.get("mainId");
        IdentifyMain identifyMain = identifyService.get(mainId);
        DictItem identifyContent = dictService.getItemByGroupKeyAndValue(Constant.IDENTIFY_TYPE_KEY, identifyMain.getContent());
        String content = identifyContent.getName();
        // 当鉴定内容为“其他”时，补充后面的输入框值
        if ("8".equals(identifyMain.getContent())) {
            content = identifyMain.getOtherContent();
        }

        Map<String, Object> result = new HashMap<>(7);
        result.put("caseNo", identifyMain.getCaseNo());
        result.put("identifyType", content);
        result.put("reportDate", DateUtil.toChar(new Date(), DateUtil.DATE_FORMAT));
        result.put("clientName", identifyMain.getClient().getClientName());
        result.put("identifyOrgName", identifyMain.getOrgName());

        return ResponseEntity.ok(result);
    }

    /**
     * 根据mainId获取编制鉴定报告信息
     *
     * @return
     */
    @RequestMapping(path = "/mainId/{mainId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getReportByMainId(@PathVariable("mainId") String mainId) {
        IdentifyReport report = identifyReportService.getByMainId(mainId);
        return ResponseEntity.ok(report);
    }


}
