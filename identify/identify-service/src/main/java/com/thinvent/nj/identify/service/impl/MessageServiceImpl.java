package com.thinvent.nj.identify.service.impl;


import com.thinvent.nj.identify.entity.Message;
import com.thinvent.nj.identify.mapper.MessageMapper;
import com.thinvent.nj.identify.service.MessageService;
import com.thinvent.nj.mybatis.service.impl.BaseCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends BaseCURDServiceImpl<Message, String> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;


    @Override
    public Integer getUnreadCount(String userId) {
        return messageMapper.getUnreadCount(userId);
    }
}
