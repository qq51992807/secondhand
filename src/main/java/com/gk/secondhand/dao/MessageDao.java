package com.gk.secondhand.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.secondhand.entity.Message;

import java.util.List;

public interface MessageDao  extends BaseMapper<Message> {

    public int messageNumberCount(int userid);
    public List<String> checkMessage(int userid);
    int noticeBuy(Message message);
    List<Message> checkMessageByUserId(int userid);
    int unreadMessage(int userid);
    int readingMessage(int userid);
}
