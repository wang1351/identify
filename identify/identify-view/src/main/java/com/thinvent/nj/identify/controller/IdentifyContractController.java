package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.service.IdentifyContractService;
import com.thinvent.nj.identify.service.IdentifyService;
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
* T_IDENTIFY_CONTRACT Controller
* @author administrator
* @date 2018-11-21
*/
@Controller
@RequestMapping(path = "/identifies/contract")
public class IdentifyContractController extends BaseViewController {

    @Autowired
    private IdentifyContractService identifyContractService;

    @Autowired
    private IdentifyService identifyService;

    @RequiresPermissions("identifyList:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {

        String mainId = request().getParameter("mainId");
        request().setAttribute("mainId", mainId);

        // claim
        identifyService.claim(mainId, UserContextUtil.userId());
        return "identifyContractList";
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        UserContextUtil.addCurrentUser(params);
        identifyContractService.insert(params);

        return ResponseEntity.ok();
    }

}
