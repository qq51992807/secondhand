package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.secondhand.dao.FriendDao;
import com.gk.secondhand.entity.Friend;
import com.gk.secondhand.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendDao, Friend> implements FriendService {

    @Resource
    private FriendDao friendDao;

    @Override
    public List<Friend> findFriendById(int id) {
        return friendDao.findFriendById(id);
    }

    @Override
    public int addNoReadNum(int uid, int bid) {
        return friendDao.addNoReadNum(uid,bid);
    }

    @Override
    public int addFriend(Friend friend) {
        return friendDao.addFriend(friend);
    }

    @Override
    public Friend selectFriendList(int uid, int bid) {
        return friendDao.selectFriendList(uid, bid);
    }

    @Override
    public int readMessage(int uid, int bid) {
        return friendDao.readMessage(uid, bid);
    }

    @Override
    public int ChatNum(int uid) {
        return friendDao.ChatNum(uid);
    }
}
