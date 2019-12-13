package com.gk.secondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.secondhand.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    public void addUser(User user);

    public User getUserByPhone(String phone);

    public void updateUserName(User user);

    int updateGoodsNum(Integer id, Integer goodsNum);

    User selectByPrimaryKey(Integer id);

    public List<User> getPageUser(int pageNum, int pageSize);

    public int getUserNum();

    public int getUserNum(String username);

    public List<User> getPageUser(int pageNum, int pageSize, String username);

    public User getUserById(int id);

    public void deleteUserById(String idArr);

    public List<User> getPageUserByUser(String phone, String username, String qq, int pageNum, int pageSize);

    public List<User> getUserOrderByDate(int size);
}
