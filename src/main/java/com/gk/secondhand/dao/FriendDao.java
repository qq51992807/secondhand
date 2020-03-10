package com.gk.secondhand.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.secondhand.entity.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendDao extends BaseMapper<Friend> {
    List<Friend> findFriendById(int id);
    int addNoReadNum(@Param("uid") int uid, @Param("bid") int bid);
    int addFriend(Friend friend);
    Friend selectFriendList(@Param("uid") int uid, @Param("bid") int bid);
    //點擊聊天框將消息設置為已讀
    int readMessage(@Param("uid") int uid, @Param("bid") int bid);
    //加载页面时显示私信数量
    int ChatNum(int uid);
 }
