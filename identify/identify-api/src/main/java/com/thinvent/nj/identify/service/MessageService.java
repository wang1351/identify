package com.thinvent.nj.identify.service;


import com.thinvent.nj.identify.entity.Message;
import com.thinvent.nj.mybatis.service.CURDService;

/**
* 站内消息服务
*/
public interface MessageService extends CURDService<Message, String>  {

    Integer getUnreadCount(String userId);

}
