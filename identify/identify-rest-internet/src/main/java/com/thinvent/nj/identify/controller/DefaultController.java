package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * Default Controller
 * @author liupeijun
 */
@Controller
public class DefaultController extends BaseController {

    @ApiIgnore
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/swagger-ui.html";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParam(name = "params", value = "请求参数Map, example: {'username': 'admin', 'password': 1}", required = true)
    public ResponseEntity login(@RequestBody Map<String, Object> params) {
        return post("/login", params, null);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity logout(@RequestHeader("token") String token) {
        return get("/logout", null, token);
    }

}