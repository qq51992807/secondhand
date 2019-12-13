package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.secondhand.dao.CatelogDao;
import com.gk.secondhand.entity.Catelog;
import com.gk.secondhand.service.CatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CatelogServiceImpl extends ServiceImpl<CatelogDao,Catelog> implements CatelogService {

    @Resource
    private CatelogDao catelogDao;

    public int getCount(Catelog catelog) {
        int count = catelogDao.getCount(catelog);
        return count;
    }
    public List<Catelog> getAllCatelog() {
        List<Catelog> catelogs = catelogDao.getAllCatelog();
        return catelogs;
    }
    public Catelog selectByPrimaryKey(Integer id){
        Catelog catelog = catelogDao.selectByPrimaryKey(id);
        return catelog;
    }
    public int updateByPrimaryKey(Catelog catelog) {
        return  catelogDao.updateByPrimaryKey(catelog);
    }
    public int updateCatelogNum(Integer id,Integer number) {
        return catelogDao.updateCatelogNum(id,number);
    }

}
