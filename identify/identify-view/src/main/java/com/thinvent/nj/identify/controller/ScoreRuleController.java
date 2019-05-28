/**
 * Copyright (C) 2018 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 类名　　       : ScoreRuleController.java
 * 功能概要       :
 * 做成日期       : 2018-11-06・panqh
 * 版权声明       : 南京思创信息技术有限公司
 */
package com.thinvent.nj.identify.controller;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.MapperUtil;
import com.thinvent.nj.identify.entity.ScoreRule;
import com.thinvent.nj.identify.entity.ScoreRuleStandard;
import com.thinvent.nj.identify.service.ScoreRuleService;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评分细则Controller
 * @author panqh
 * @date 2018-11-06
 */
@Controller
@RequestMapping(path = "/setting/scores")
public class ScoreRuleController extends BaseViewController {

    @Autowired
    private ScoreRuleService scoreRuleService;

    @RequiresPermissions("score:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        return "scoreRuleList";
    }

    @RequestMapping(path = "/scoreType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity findScoreTypeList() throws Exception {
        List<Map<String, Object>> targets = new ArrayList<>(3);
        Map<String, Object> mapScoreType = null;
        for (int i = 1; i <= 3; i++) {
            mapScoreType = new HashMap<>(2);
            mapScoreType.put("key", i);
            if (i == 1) {
                mapScoreType.put("value", "基本信息");
            } else if (i == 2) {
                mapScoreType.put("value", "良好信息");
            } else {
                mapScoreType.put("value", "不良信息");
            }

            targets.add(mapScoreType);
        }

        return ResponseEntity.ok(targets);
    }

    @RequestMapping(path = "/scoreRule/{key}/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@PathVariable("key") String key) {
        List<ScoreRuleStandard> targets = scoreRuleService.getScoreRuleStandardListByScoreType(key);

        JSONObject jsonObject = new JSONObject(1);
        jsonObject.put("result", targets);

        return ResponseEntity.ok(jsonObject);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(@PathVariable("id") String id) {
        return ResponseEntity.ok(scoreRuleService.get(id));
    }

    //update by wangwj 20190121 start
    @RequiresPermissions("score:update")
    @RequestMapping(path = "/saveScore", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveScore(@RequestBody Map<String, Object> params) {
        String id = (String) params.get("id");
        String score = (String) params.get("score");
        String standard =(String) params.get("standard");
        String standardDetail =(String) params.get("standardDetail");
        ScoreRuleStandard target = scoreRuleService.getScoreRuleStandardById(id);
        target.setScoreValue(score);
        target.setScoreStandard(standard);
        target.setScoreStandardDetail(standardDetail);
        scoreRuleService.saveScoreValue(target);
        return ResponseEntity.ok();
    }
    //update by wangwj 20190121 end

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) {
        ScoreRule target = new ScoreRule();
        MapperUtil.copy(params, target);
        scoreRuleService.insert(target);
        return ResponseEntity.ok();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) {
        ScoreRule target = scoreRuleService.get(id);
        MapperUtil.copy(params, target);
        scoreRuleService.update(target);
        return ResponseEntity.ok();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity del(@PathVariable("id") String id) {
        scoreRuleService.delete(id);
        return ResponseEntity.ok();
    }
}
