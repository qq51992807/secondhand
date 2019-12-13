package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.gk.secondhand.entity.CommentExtend;
import com.gk.secondhand.entity.Comments;
import com.gk.secondhand.entity.Goods;
import com.gk.secondhand.dao.GoodsDao;
import com.gk.secondhand.service.GoodsService;
import com.gk.secondhand.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao,Goods> implements GoodsService {

    @Resource
    GoodsDao GoodsDao;

    public int addGood(Goods goods , Integer duration) {
        String startTime = DateUtil.getNowDay();
        String endTime = DateUtil.getLastTime(startTime, duration);
        String polishTime = startTime;
        //添加上架时间，下架时间，擦亮时间
        goods.setPolishTime(polishTime);
        goods.setEndTime(endTime);
        goods.setStartTime(startTime);
        return GoodsDao.insert(goods);
    }

    public Goods getGoodsByPrimaryKey(Integer goodsId) {
        Goods goods = GoodsDao.selectByPrimaryKey(goodsId);
        return goods;
    }

    public Goods getGoodsById(Integer goodsId) {
        Goods goods = GoodsDao.selectById(goodsId);
        return goods;
    }

    public void deleteGoodsByPrimaryKey(Integer id) {
        GoodsDao.deleteByPrimaryKey(id);
    }

    public void deleteGoodsByPrimaryKeys(Integer id) {
        GoodsDao.deleteByPrimaryKeys(id);
    }

    public List<Goods> getAllGoods() {
        List<Goods> goods = GoodsDao.selectAllGoods();
        return goods;
    }

    public List<Goods> searchGoods(String name, String describle) {
        List<Goods> goods = GoodsDao.searchGoods(name,describle);
        return  goods;
    }

    public List<Goods> getGoodsByStr(Integer limit,String name,String describle) {
        List<Goods> goods = GoodsDao.selectByStr(limit, name, describle);
        return goods;
    }

    public List<Goods> getGoodsByCatelog(Integer id,String name,String describle) {
        List<Goods> goods = GoodsDao.selectByCatelog(id,name,describle);
        return goods;
    }

    public void updateGoodsByPrimaryKeyWithBLOBs(int goodsId,Goods goods) {
        goods.setId(goodsId);
        this.GoodsDao.updateByPrimaryKeyWithBLOBs(goods);
    }

    public List<Goods> getGoodsByCatelogOrderByDate(Integer catelogId,Integer limit) {
        List<Goods> goodsList = GoodsDao.selectByCatelogOrderByDate(catelogId , limit);
        return goodsList;
    }

    public List<Goods> getGoodsOrderByDate(Integer limit) {
        List<Goods> goodsList = GoodsDao.selectOrderByDate(limit);
        return goodsList;
    }

    public List<Goods> getGoodsByUserId(Integer user_id) {
        List<Goods> goodsList = GoodsDao.getGoodsByUserId(user_id);
        return goodsList;
    }

    public void updateGoodsByGoodsId(Goods goods) {

        GoodsDao.updateGoodsByGoodsId(goods);

    }

    @Override
    public int getGoodsNum() {
        //获取出商品的数量
        List<Goods> goods = GoodsDao.getGoodsList();
        return goods.size();
    }

    @Override
    public List<Goods> getPageGoods(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list =GoodsDao.getGoodsList();
        return list;
    }

    @Override
    public List<Goods> getPageGoodsByGoods(Integer id,String name,Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list =GoodsDao.getPageGoodsByGoods(id,name,status);
        return list;
    }

    @Override
    public CommentExtend selectCommentsByGoodsId(Integer id) {
        return GoodsDao.selectCommentsByGoodsId(id);
    }

    @Override
    public void addComments(Comments comments) {
        GoodsDao.addComments(comments);
    }
}
