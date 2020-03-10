package com.gk.secondhand.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.secondhand.entity.Chat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatDao extends BaseMapper<Chat> {
    List<Chat>  selectHistoryList(@Param("uid") int uid, @Param("bid") int bid);
    int sendChat(Chat chat);
    //点击聊天框时将消息标记为已读
    int readHistory(@Param("uid") int uid, @Param("bid") int bid);
}
