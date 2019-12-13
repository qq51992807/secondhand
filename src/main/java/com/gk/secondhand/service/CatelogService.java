package com.gk.secondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.secondhand.entity.Catelog;

import java.util.List;

public interface CatelogService extends IService<Catelog> {
    public List<Catelog> getAllCatelog();
    public int getCount(Catelog catelog);
    Catelog selectByPrimaryKey(Integer id);
    int updateByPrimaryKey(Catelog record);
    int updateCatelogNum(Integer id,Integer number);
}
