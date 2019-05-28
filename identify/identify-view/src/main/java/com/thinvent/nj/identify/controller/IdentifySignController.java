package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.identify.service.IdentifySignService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* 鉴定报告签发 Controller
* @author liupj
* @date 2018-11-21
*/
@Controller
@RequestMapping(path = "/identifies/sign")
public class IdentifySignController extends BaseViewController {

    @Autowired
    private IdentifySignService identifySignService;

    @Autowired
    private IdentifyService identifyService;

    @RequiresPermissions("identifyList:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        String mainId = request().getParameter("mainId");
        request().setAttribute("mainId", mainId);

        // claim
        identifyService.claim(mainId, UserContextUtil.userId());
        return "identifySignList";
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        params.put("curUserId", UserContextUtil.userId());
        params.put("curFullName", UserContextUtil.fullName());

        identifySignService.insert(params);

        return ResponseEntity.ok();
    }


}
