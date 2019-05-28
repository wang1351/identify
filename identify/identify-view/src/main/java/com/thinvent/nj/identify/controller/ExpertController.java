package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.identify.entity.Expert;
import com.thinvent.nj.identify.service.ExpertService;
import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.uc.service.DictService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 鉴定专家 Controller
* @author xujc
* @date 2018-10-31
*/
@Controller
@RequestMapping(path = "/library/experts")
public class ExpertController extends BaseViewController {

    @Autowired
    private ExpertService expertService;

    @Autowired
    private DictService dictService;

    @RequiresPermissions("expert:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        return "expertList";
    }


    @RequiresPermissions("expert:view")
    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Page<Expert> target = expertService.findPage(getQueryParameter(params), getQueryMsg(params));
        return ResponseEntity.ok(target);
    }

    @RequiresPermissions("expert:view")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(@PathVariable("id") String id) {
        Expert expert = expertService.get(id);
        return ResponseEntity.ok(expert);
    }

    /**
     * 获取专家列表
     * @author panqh
     * @date 2018-11-28
     * @return
     */
    @RequestMapping(path = "/getExpertList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findExpertList() {
        Map<String,Object> condition=new HashMap<>();
        condition.put("status", 1);
        List<Expert> targets = expertService.findList(condition);
        List<Map<String, Object>> lstMap = new ArrayList<>();
        Map<String, Object> map = null;
        for (Expert expert : targets) {
            map = new HashMap<>(2);
            map.put("id", expert.getId());
            if(StringUtil.isNullOrEmpty(expert.getTitle())){
                map.put("name", expert.getName() + "（" +  expert.getPhone() + "）");
            }else {
                map.put("name", expert.getName() + "（" + expert.getTitle() + " " + expert.getPhone() + "）");
            }


            lstMap.add(map);
        }
        return ResponseEntity.ok(lstMap);
    }

    @RequiresPermissions("expert:add")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insert(@RequestBody Map<String, Object> params) throws Exception{
        expertService.addExpert(params);
        return ResponseEntity.ok();
    }


    @RequiresPermissions("expert:update")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Map<String, Object> params) throws Exception {
        expertService.updateExpert(id,params);
        return ResponseEntity.ok();
    }


    @RequiresPermissions("expert:delete")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity del(@PathVariable("id") String id) {
        Expert target = expertService.get(id);
        expertService.deleteExpert(target);
        return ResponseEntity.ok();
    }



}
