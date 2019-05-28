package com.thinvent.nj.identify.entity;

import com.thinvent.nj.mybatis.entity.BaseOracleEntity;

import java.util.Date;

public class Message extends BaseOracleEntity {

    // 消息标题
    private String title;

    // 消息内容
    private String content;

    // 消息插入时间
    private Date addTime;

    // 消息读取时间
    private Date readTime;

    // 消息状态（0： 未读；1：已读）
    private Integer status;

    // 消息类型（1: 鉴定系统消息；2: 危房监管消息）
    private Integer msgType;

    // 消息接收人ID
    private String receiveUserId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }
}
