package com.thinvent.nj.identify.controller.sys;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.service.DictService;
import com.thinvent.nj.web.controller.BaseViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* 字典管理
*/
@Controller
@RequestMapping(path = "/sys/dicts")
public class DictController extends BaseViewController {

    @Autowired
    private DictService dictService;

    @RequestMapping(path = "/groups/keys/{groupKey}/items", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findDictItems(@PathVariable("groupKey") String groupKey) {
        List<DictItem> targets = dictService.getItemsByGroupKey(groupKey);
        return ResponseEntity.ok(targets);
    }

    @RequestMapping(path = "/groups/keys/{groupKey}/{pId}/items", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findDictItems(@PathVariable("groupKey") String groupKey,
                                        @PathVariable("pId") String pId) {
        List<DictItem> targets = dictService.getItemsByGroupKeyAndPId(groupKey, pId);
        return ResponseEntity.ok(targets);
    }
}
