package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.entity.*;
import com.thinvent.nj.identify.service.*;
import com.thinvent.nj.uc.entity.Org;
import com.thinvent.nj.uc.service.OrgService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.http.HttpResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* T_IDENTIFY_MAIN Controller
* @author administrator
* @date 2018-11-13
*/
@Controller
@RequestMapping(path = "/identifies")
public class IdentifyMainController extends BaseViewController {

    @Autowired
    private IdentifyMainService identifyMainService;
    @Autowired
    private IdentifyAcceptService identifyAcceptService;
    @Autowired
    private IdentifyPreviewService identifyPreviewService;
    @Autowired
    private IdentifyPlanService identifyPlanService;
    @Autowired
    private IdentifyContractService identifyContractService;
    @Autowired
    private IdentifyReportService identifyReportService;
    @Autowired
    private IdentifySignService identifySignService;
    @Autowired
    private IdentifySendService identifySendService;
    @Autowired
    private IdentifyRecordService identifyRecordService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private IdentifyVerifyService identifyVerifyService;
    // update by xujc 2019/3/12 start
    @RequiresPermissions("identifyList:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        // 当前用户的机构对象
        Org org = orgService.get(UserContextUtil.orgId());
        // 鉴定处对象
        Org jdOrg = orgService.getByCode(Constant.IDENTIFY_DEP_CODE);
        if(org.getPId().equals(jdOrg.getId())){
            request().setAttribute("isJdc",1);
        }
        return "identifyMainList";
    }
    // update by xujc 2019/3/12 end

    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        // add by panqh 2018-11-27 start
        Map<String, Object> condition = getQueryMsg(params);
       String nowSearch = (String)condition.get("nowSearch");
        if("12".equals(nowSearch)){
            condition.put("suspend",2);
            condition.remove("nowSearch");
        }else if("13".equals(nowSearch)){
            condition.put("suspend",1);
            condition.remove("nowSearch");
        }
        String startTime = (String) condition.get("startTime");
        String endTime = (String) condition.get("endTime");
        if (!StringUtil.isNullOrEmpty(startTime)) {
            condition.put("startTime", startTime + " 00:00:00");
        }
        if (!StringUtil.isNullOrEmpty(endTime)) {
            condition.put("endTime", endTime + " 23:59:59");
        }
        // 当前用户的机构对象
        Org org = orgService.get(UserContextUtil.orgId());
        // 鉴定处对象
        Org jdOrg = orgService.getByCode(Constant.IDENTIFY_DEP_CODE);
        // 如果当前机构对象的父id等于鉴定处的id
        if(org.getPId().equals(jdOrg.getId())){
            condition.put("orgSearch",org.getId());
        }
        // add by panqh 2018-11-27 end
        Page<IdentifyMain> target = identifyMainService.findPage(getQueryParameter(params), condition);
        return ResponseEntity.ok(target);
    }

    /**
     * @author : wangwj
     * @date :2018/11/27
     * @Description : 根据鉴定单位的ID在页面中筛选鉴定单位
     */
     @RequestMapping(path = "/getOrgListByPid", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity getOrgListByPid(){
         Map<String, Object> condition = new HashMap<>();
         Org org = orgService.getByCode(Constant.IDENTIFY_DEP_CODE);
         condition.put("pId", org.getId());

         List<Org> list = orgService.getOrgListByCondition(condition);
         return ResponseEntity.ok(list);
     }

    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    public String detailPage() {

        return "detail";
    }

    /**
     * @author : xujc
     * @date :2018/11/27
     * @Description : 根据主表id获取委托人申请鉴定
     */
    @RequestMapping(path = "/getMain/{mainId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getMian(@PathVariable String mainId) {
        IdentifyMain main = identifyMainService.get(mainId);
        return ResponseEntity.ok(main);
    }
    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据主表id获取受理对象
     */
    @RequestMapping("/getAccept/{mainId}")
    @ResponseBody
    public ResponseEntity getAcceptByMainId(@PathVariable String mainId){
        IdentifyAccept accept = identifyAcceptService.getAcceptByMainId(mainId);
        return ResponseEntity.ok(accept);
    }

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据主表id获取初勘对象
     */
    @RequestMapping("/getPreview/{mainId}")
    @ResponseBody
    public ResponseEntity getPreviewByMainId(@PathVariable String mainId){
        return ResponseEntity.ok(identifyPreviewService.getPreviewByMainId(mainId));
    }
    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据主表id获取出具方案对象
     */
    @RequestMapping("/getPlan/{mainId}")
    @ResponseBody
    public ResponseEntity getPlanByMainId(@PathVariable String mainId){
        return ResponseEntity.ok(identifyPlanService.getPlanByMainId(mainId));
    }

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据主表id获取签订合同对象
     */
    @RequestMapping("/getContract/{mainId}")
    @ResponseBody
    public ResponseEntity getContractByMainId(@PathVariable String mainId){
        IdentifyContract contract=identifyContractService.getContractByMainId(mainId);
        return ResponseEntity.ok(contract);
//        return ResponseEntity.ok(identifyContractService.getContractByMainId(mainId));


    }
    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据主键id获取编制鉴定报告对象
     */
    @RequestMapping("/getReport/{id}")
    @ResponseBody
    public ResponseEntity getReportById(@PathVariable String id){
        return ResponseEntity.ok(identifyReportService.get(id));
    }

    /**
     * @author : xujc
     * @date :2018/12/12
     * @Description : 根据主键id获取审核对象
     */
    @RequestMapping("/getVerify/{id}")
    @ResponseBody
    public ResponseEntity getVerifyById(@PathVariable String id){
        return ResponseEntity.ok(identifyVerifyService.get(id));
    }
    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据主表id获取签发鉴定对象
     */
    @RequestMapping("/getSign/{mainId}")
    @ResponseBody
    public ResponseEntity getSignByMainId(@PathVariable String mainId){
        return ResponseEntity.ok(identifySignService.getSignByMainId(mainId));
    }
    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据主表id获取发放鉴定报告对象
     */
    @RequestMapping("/getSend/{mainId}")
    @ResponseBody
    public ResponseEntity getSendByMainId(@PathVariable String mainId){
        return ResponseEntity.ok(identifySendService.getSendByMainId(mainId));
    }

    /**
     * @author : xujc
     * @date :2018/11/23
     * @Description : 根据主表id获取recoud对象集合
     */
    @RequestMapping("/getRecoud/{mainId}")
    @ResponseBody
    public ResponseEntity getRecoudByMainId(@PathVariable String mainId){
        Map<String,Object> map=new HashMap<>();
        map.put("mainId",mainId);
        List<IdentifyRecord> identifyRecouds = identifyRecordService.findList(map);
        return ResponseEntity.ok(identifyRecouds);
    }

    /**
     * @author : xujc
     * @date :2019/1/15
     * @Description : TODO
     */
    @RequestMapping("/getRecoudByStatus")
    @ResponseBody
    public ResponseEntity getStopRecoud(@RequestBody Map<String,Object> map){

        return ResponseEntity.ok(identifyRecordService.findList(map).get(0));
    }

    @PostMapping(path = "/{mainId}/houseAddress")
    @ResponseBody
    public ResponseEntity getHouseAddress(@PathVariable("mainId") String mainId) {
        IdentifyMain identifyMain = identifyMainService.get(mainId);
        House house = identifyMain.getHouse();

        Map<String, Object> result = new HashMap<>(1);
        result.put("houseAddress", house.getZone() + house.getStreet() + house.getAddress());

        return ResponseEntity.ok(result);
    }


}
