package com.thinvent.nj.identify.mapper;


import com.thinvent.nj.identify.entity.Message;
import com.thinvent.nj.mybatis.mapper.CURDMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper extends CURDMapper<Message, String> {

    Integer getUnreadCount(String userId);
}
