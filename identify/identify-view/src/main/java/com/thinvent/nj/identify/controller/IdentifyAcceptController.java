package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.constant.Constant;
import com.thinvent.nj.identify.service.IdentifyAcceptService;
import com.thinvent.nj.identify.service.IdentifyService;
import com.thinvent.nj.uc.entity.User;
import com.thinvent.nj.uc.service.UserService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * T_IDENTIFY_ACCEPT Controller
 *
 * @author xujc
 * @date 2018-11-21
 */
@Controller
@RequestMapping(path = "/identifies/accept")
public class IdentifyAcceptController extends BaseViewController {

    @Autowired
    private IdentifyAcceptService identifyAcceptService;
    @Autowired
    private IdentifyService identifyService;
    @Autowired
    private UserService userService;


    @RequiresPermissions("identify:accept")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        String mainId = request().getParameter("mainId");
        request().setAttribute("mainId", mainId);

        // claim 签收任务
        identifyService.claim(mainId, UserContextUtil.userId());

        return "identifyAcceptList";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        UserContextUtil.addCurrentUser(params);
        identifyAcceptService.insert(params);

        return ResponseEntity.ok();
    }

    /**
     * @author : xujc
     * @date :2018/11/22
     * @Description : 获取初勘负责人
     */

    @RequestMapping(path = "/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getUsers() {
        List<User> userList = userService.findByOrgIdAndRoleCode(UserContextUtil.orgId(), Constant.ROLE_CODE_PREVIEW);
        return ResponseEntity.ok(userList);
    }

    /**
     * @author : xujc
     * @date :2018/11/22
     * @Description : 获取初勘参与人员
     */

    @RequestMapping(path = "/getOrgUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getOrgUsers() {
        List<User> userList = userService.findByOrgId(UserContextUtil.orgId());
        return ResponseEntity.ok(userList);
    }


}
