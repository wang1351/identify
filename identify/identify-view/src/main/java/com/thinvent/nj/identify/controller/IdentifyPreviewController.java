package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.service.IdentifyPreviewService;
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
* 初勘 Controller
* @author liupj
* @date 2018-11-21
*/
@Controller
@RequestMapping(path = "/identifies/preview")
public class IdentifyPreviewController extends BaseViewController {

    @Autowired
    private IdentifyPreviewService identifyPreviewService;

    @RequiresPermissions("identifyList:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        request().setAttribute("mainId", request().getParameter("mainId"));
        return "identifyPreviewList";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        UserContextUtil.addCurrentUser(params);

        // 保存初勘信息
        identifyPreviewService.insert(params);

        return ResponseEntity.ok();
    }


}
