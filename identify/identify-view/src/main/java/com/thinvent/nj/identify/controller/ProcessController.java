package com.thinvent.nj.identify.controller;

import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作流相关Controller
 * @author liupeijun
 */
@Controller
public class ProcessController {

    @Value("${activiti.rest.prefix}")
    private String activitiUrl;

    @Autowired
    private IdentifyService identifyService;

    @RequestMapping(path = "/processes/{mainId}/diagram")
    public String showDiagram(@PathVariable("mainId") String mainId) {
        String instanceId = identifyService.getInstanceId(mainId, UserContextUtil.userId());

         return "redirect:" + activitiUrl + "/instances/" + instanceId + "/diagram";
    }
 }
