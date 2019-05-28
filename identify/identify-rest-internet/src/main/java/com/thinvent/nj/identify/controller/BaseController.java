package com.thinvent.nj.identify.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.HttpUtil;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * base Controller for rest
 * @author liupeijun
 * @date 2018/05/28
 *
 */
public abstract class BaseController extends BaseViewController {


    @Value("${rest.url}")
    private String restUrl;


    protected ResponseEntity post(String path, Map<String, Object> params, String token) {
        return getOrPost(path, params, token, HttpMethod.POST);
    }

    protected ResponseEntity get(String path, Map<String, Object> params, String token) {
        return getOrPost(path, params, token, HttpMethod.GET);
    }


    private ResponseEntity getOrPost(String path, Map<String, Object> params, String token, HttpMethod method) {
        String url = restUrl + path;

        Map<String, Object> header = new HashMap<>(1);
        if (!StringUtil.isNullOrEmpty(token)) {
            header.put("token", token);
        }

        if (params == null) {
            params = new HashMap<>(1);
        }

        String response;

        if (method == HttpMethod.GET) {
            response = HttpUtil.getJSON(url, params, header);
        } else if (method == HttpMethod.POST) {
            response = HttpUtil.postJSON(url, params, header);
        } else {
            throw new IllegalArgumentException("wrong type");
        }


        JSONObject jsonObject = JSON.parseObject(response);
        boolean isSuccess = jsonObject.getBooleanValue("success");

        if (isSuccess) {
            return ResponseEntity.ok(jsonObject.get("data"));
        }

        return ResponseEntity.fail(HttpStatus.valueOf(jsonObject.getIntValue("httpStatus")), jsonObject.getString("msg"));
    }
}