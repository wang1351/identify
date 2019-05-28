package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.service.IdentifySendService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* 鉴定报告发送 Controller
* @author liupj
* @date 2018-11-21
*/
@Controller
@RequestMapping(path = "/identifies/send")
public class IdentifySendController extends BaseViewController {

    @Autowired
    private IdentifySendService identifySendService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        params.put("curUserId", UserContextUtil.userId());
        params.put("curFullName", UserContextUtil.fullName());

        identifySendService.insert(params);

        return ResponseEntity.ok();
    }



}
