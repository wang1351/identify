package com.thinvent.nj.identify.controller;

import com.alibaba.fastjson.JSONArray;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.entity.IdentifyOrgMain;
import com.thinvent.nj.identify.service.IdentifyOrgService;
import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
* 鉴定机构名录库 Controller
* @author panqh
* @date 2018-11-01
*/
@Controller
@RequestMapping(path = "/library/identify/orgs")
public class IdentifyOrgController extends BaseViewController {

    @Autowired
    private IdentifyOrgService identifyOrgService;

    @RequiresPermissions("identifyOrg:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        return "identifyOrgList";
    }


    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Page<IdentifyOrgMain> target = identifyOrgService.findPage(getQueryParameter(params), getQueryMsg(params));
        return ResponseEntity.ok(target);
    }

    @RequiresPermissions("identifyOrg:detail")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(@PathVariable("id") String id) {

        IdentifyOrgMain identifyOrg = identifyOrgService.get(id);


        return ResponseEntity.ok(identifyOrg);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        JSONArray identifyBusiness = (JSONArray) params.get("identifyBusinessKey");

        IdentifyOrgMain target = new IdentifyOrgMain();
        MapperUtil.copy(params, target);

        String identifyOrgId = identifyOrgService.saveIdentifyOrg(target).getId();

        identifyOrgService.saveIdentifyOrgType(identifyOrgId, identifyBusiness);

        return ResponseEntity.ok();
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) {
        JSONArray identifyBusiness = (JSONArray) params.get("identifyBusinessKey");

        identifyOrgService.saveIdentifyOrgType(id, identifyBusiness);

        return ResponseEntity.ok();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity del(@PathVariable("id") String id) {
        identifyOrgService.delete(id);
        return ResponseEntity.ok();
    }

    @RequiresPermissions("identifyOrg:useOrUnUse")
    @RequestMapping(path = "/{type}/{idS}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity useOrUnUse(@PathVariable("type") String type, @PathVariable("idS") String idS) {
        identifyOrgService.identifyOrgUseOrUnUse(type, idS);
        return ResponseEntity.ok();
    }

   /* @RequestMapping(path = "/existOrgCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity existOrgCode(@RequestBody Map<String, Object> params) {
        String identifyOrgId = (String) params.get("identifyOrgId");
        String identifyOrgCode = (String) params.get("identifyOrgCode");

        boolean existFlag = identifyOrgService.existOrgCode(identifyOrgId, identifyOrgCode);

        if (existFlag) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }
    }*/


}
