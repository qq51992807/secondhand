package com.gk.secondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.secondhand.entity.Chat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatService extends IService<Chat> {
    List<Chat> selectHistoryList(int uid, int bid);
    int sendChat(Chat chat);
    //点击聊天框时将消息标记为已读
    int readHistory(@Param("uid") int uid, @Param("bid") int bid);
}
