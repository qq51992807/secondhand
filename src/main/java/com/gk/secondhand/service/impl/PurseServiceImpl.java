package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.gk.secondhand.dao.PurseDao;
import com.gk.secondhand.entity.Purse;
import com.gk.secondhand.service.PurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurseServiceImpl extends ServiceImpl<PurseDao,Purse> implements PurseService {

    @Resource
    PurseDao purseDao;

    @Override
    public void updatePurseByuserId(Integer userId, Float balance) {
        // TODO Auto-generated method stub
        purseDao.updatePurseByuserId(userId, balance);

    }


    @Override
    public void updatePurseOfdel(Integer userId, Float balance) {
        // TODO Auto-generated method stub
        purseDao.updatePurseOfdel(userId, balance);
    }


    @Override
    public void addPurse(Integer userId) {
        // TODO Auto-generated method stub
        purseDao.addPurse(userId);
    }


    @Override
    public Purse getPurseByUserId(Integer user_id) {
        // TODO Auto-generated method stub
        return purseDao.selectPurseByUserId(user_id);
    }


    @Override
    public void updatePurse(Purse purse) {
        // TODO Auto-generated method stub
        purseDao.updatePurse(purse);

    }


    @Override
    public int getPurseNum() {
        List<Purse> purse= purseDao.selectPurseList();
        return purse.size();
    }


    @Override
    public List<Purse> getPagePurse(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Purse> purse =purseDao.selectPurseList();
        return purse;
    }


    @Override
    public List<Purse> getPagePurseByPurse(Integer userId,Integer state, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Purse> purse =purseDao.getPagePurseByPurse(userId,state);
        return purse;
    }


    @Override
    public Purse getPurseById(int id) {
        return purseDao.selectPurseById(id);
    }


    @Override
    public void updateByPrimaryKey(Integer id, Purse purse) {
        purse.setId(id);
        purseDao.updateByPrimaryKey(purse);

    }


    @Override
    public void updatePursePassById(Integer id,Purse purse) {
        purse.setId(id);
        purseDao.updatePurseById(purse);

    }


    @Override
    public void updatePurseRefuseById(Integer id,Purse purse) {
        purse.setId(id);
        purseDao.updatePurseById(purse);

    }


}
