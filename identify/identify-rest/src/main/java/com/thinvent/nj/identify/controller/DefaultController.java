package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.uc.entity.User;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * Default Controller
 * @author liupeijun
 */
@Controller
public class DefaultController extends BaseViewController {

    @ApiIgnore
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/swagger-ui.html";
    }

    @ApiIgnore
    @RequestMapping(path = "/login" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity login() {
        return ResponseEntity.fail(HttpStatus.UNAUTHORIZED, "认证失败!");
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestBody Map<String, String> params) {
        Subject subject = SecurityUtils.getSubject();
        String username = params.get("username");
        String password = params.get("password");

        subject.login(new UsernamePasswordToken(username, password));

        User currentUser = UserContextUtil.currentUser();
        currentUser.setToken((String) request().getAttribute("token"));

        return ResponseEntity.ok(currentUser);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return ResponseEntity.ok();
    }
}
