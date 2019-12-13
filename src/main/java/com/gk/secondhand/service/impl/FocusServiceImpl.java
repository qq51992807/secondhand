package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.secondhand.dao.FocusDao;
import com.gk.secondhand.entity.Focus;
import com.gk.secondhand.service.FocusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FocusServiceImpl extends ServiceImpl<FocusDao,Focus> implements FocusService {

    @Resource
    FocusDao focusDao;

    /**
     * 根据用户id获取我的关注
     */
    public List<Focus> getFocusByUserId(Integer user_id) {
        List<Focus> focusList = focusDao.getFocusByUserId(user_id);

        return focusList;
    }

    /*
     * 根据用户id和关注id删除
     */

    public void deleteFocusByUserIdAndGoodsId(Integer goods_id, Integer user_id) {

        focusDao.deleteFocusByUserIdAndGoodsId(goods_id, user_id);

    }
    /*
     * 添加我的关注
     */
    public void addFocusByUserIdAndId(Integer goods_id, Integer user_id) {

        focusDao.addFocusByUserIdAndGoodsId(goods_id,user_id);

    }
}
