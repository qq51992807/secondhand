package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.secondhand.dao.MessageDao;
import com.gk.secondhand.entity.Message;
import com.gk.secondhand.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao,Message> implements MessageService {

    @Resource
    private MessageDao messageDao;


    @Override
    public List<String> checkMessage(int userid) {
        return messageDao.checkMessage(userid);
    }

    @Override
    public int messageNumberCount(int userid) {
        return messageDao.messageNumberCount(userid);
    }

    @Override
    public int noticeBuy(Message message) {
        return messageDao.noticeBuy(message);
    }

    @Override
    public List<Message> checkMessageByUserId(int userid) {
        return messageDao.checkMessageByUserId(userid);
    }

    @Override
    public int unreadMessage(int userid) {
        return messageDao.unreadMessage(userid);
    }

    @Override
    public int readingMessage(int userid) {
        return messageDao.readingMessage(userid);
    }
}

