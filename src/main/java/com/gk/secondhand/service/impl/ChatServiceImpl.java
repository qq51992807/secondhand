package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.secondhand.dao.ChatDao;
import com.gk.secondhand.entity.Chat;
import com.gk.secondhand.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatDao, Chat> implements ChatService {

    @Resource
    private ChatDao chatDao;

    @Override
    public List<Chat> selectHistoryList(int uid, int bid) {
        return chatDao.selectHistoryList(uid,bid);
    }

    @Override
    public int sendChat(Chat chat) {
        return chatDao.sendChat(chat);
    }

    @Override
    public int readHistory(int uid, int bid) {
        return chatDao.readHistory(uid, bid);
    }
}
