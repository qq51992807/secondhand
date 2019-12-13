package com.gk.secondhand.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.secondhand.entity.Purse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurseDao extends BaseMapper<Purse> {
    public void updatePurseByuserId(@Param("userId") Integer userId, @Param("balance") Float balance);

    public void updatePurseOfdel(@Param("userId") Integer userId, @Param("balance") Float balance);

    public void addPurse(Integer userId);

    public Purse selectPurseByUserId(Integer user_id);

    public void updatePurse(Purse purse);

    public List<Purse> selectPurseList();

    public List<Purse> getPagePurseByPurse(@Param("userId")Integer userId, @Param("state")Integer state);

    public Purse selectPurseById(int id);

    public void updateByPrimaryKey(Purse purse);

    public void updatePurseById(Purse purse);
}
