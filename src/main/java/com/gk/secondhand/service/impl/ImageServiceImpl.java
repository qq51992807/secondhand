package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.secondhand.dao.ImageDao;
import com.gk.secondhand.entity.Image;
import com.gk.secondhand.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageDao,Image> implements ImageService {

@Resource
ImageDao imageDao;

    public int insert(Image record) {
        return imageDao.insert(record);
    }
    public List<Image> getImagesByGoodsPrimaryKey(Integer goodsId) {
        List<Image> image = imageDao.selectByGoodsPrimaryKey(goodsId);
        return image;
    }
    public int deleteImagesByGoodsPrimaryKey(Integer goodsId) {
        return imageDao.deleteImagesByGoodsPrimaryKey(goodsId);
    }
}
