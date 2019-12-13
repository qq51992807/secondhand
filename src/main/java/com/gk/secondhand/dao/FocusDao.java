package com.gk.secondhand.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.secondhand.entity.Focus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FocusDao extends BaseMapper<Focus> {
    /**
     * 查询登录用户的所有关注商品
     * @param user_id
     * @return
     */
    public List<Focus> getFocusByUserId(Integer user_id);

    /**
     * 根据用户id和关注id删除关注的商品
     * @param id 关注id
     * @param user_id 用户id
     */

    public void deleteFocusByUserIdAndGoodsId(@Param("goodId") Integer goods_id,@Param("userId") Integer user_id);

    /**
     * 添加关注
     * @param goods_id
     * @param user_id
     */
    public void addFocusByUserIdAndGoodsId(@Param("goodId") Integer goods_id, @Param("userId") Integer user_id);
}
