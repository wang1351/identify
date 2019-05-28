package com.thinvent.nj.expert.controller.sys;

import com.thinvent.nj.common.page.Page;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.identify.entity.Correct;
import com.thinvent.nj.identify.entity.Message;
import com.thinvent.nj.identify.service.MessageService;
import com.thinvent.nj.uc.entity.DictItem;
import com.thinvent.nj.uc.service.DictService;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import com.thinvent.nj.web.controller.BaseViewController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* 字典管理
*/
@Controller
@RequestMapping(path = "/sys/msgs")
public class MsgController extends BaseViewController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        return "msgList";
    }

    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        Map<String, Object> condition = getQueryMsg(params);
        condition.put("userId", UserContextUtil.userId());

        Page<Message> target = messageService.findPage(getQueryParameter(params), condition);

        return ResponseEntity.ok(target);
    }

    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    public String toDetailHtml() {
        Message message = messageService.get(request().getParameter("id"));
        request().setAttribute("message", message);

        return "msgDetail";
    }

    @RequestMapping(path = "/{id}/readed", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity readed(@PathVariable("id") String id) {
        Message message = messageService.get(id);
        messageService.update(message);

        return ResponseEntity.ok();
    }

    @RequestMapping(path = "/unread/counts", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity unreadCount() {
        Integer count = 0;
        try {
            count = messageService.getUnreadCount(UserContextUtil.userId());
        } catch (Exception e) {
            // ignore
        }


        return ResponseEntity.ok(count);
    }
}
