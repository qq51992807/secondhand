package com.gk.secondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.secondhand.entity.Message;

import java.util.List;

public interface MessageService extends IService<Message> {

    public List<String> checkMessage(int userid);

    public int messageNumberCount(int userid);
    //物品被买下后通知相对应的卖家
    int noticeBuy(Message message);

    List<Message> checkMessageByUserId(int userid);
    int unreadMessage(int userid);

    int readingMessage(int userid);
}
