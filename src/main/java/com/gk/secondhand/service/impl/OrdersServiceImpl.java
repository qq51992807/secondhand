package com.gk.secondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.gk.secondhand.dao.OrdersDao;
import com.gk.secondhand.entity.Orders;
import com.gk.secondhand.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersDao,Orders> implements OrdersService {

    @Resource
    private OrdersDao ordersDao;

    public List<Orders> getOrdersByUserId(Integer user_id) {
        // TODO Auto-generated method stub
        List<Orders> orders=ordersDao.selectOrdersByUserId(user_id);
        return orders;
    }

    @Override
    public List<Orders> getOrdersByUserAndGoods(Integer user_id) {
        // TODO Auto-generated method stub

        List<Orders> ordersOfSell=ordersDao.selectOrdersByUserAndGoods(user_id);
        return ordersOfSell;
    }

    @Override
    public void addOrders(Orders orders) {
        // TODO Auto-generated method stub

        ordersDao.addOrders(orders);

    }

    @Override
    public void deliverByOrderNum(Integer orderNum) {
        // TODO Auto-generated method stub
        ordersDao.deliverByOrderNum(orderNum);
    }

    @Override
    public void receiptByOrderNum(Integer orderNum) {
        // TODO Auto-generated method stub
        ordersDao.receiptByOrderNum(orderNum);

    }

    @Override
    public int getOrdersNum() {
        List<Orders> orders = ordersDao.getOrdersList();
        return orders.size();
    }

    @Override
    public List<Orders> getPageOrders(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list =ordersDao.getOrdersList();
        return list;
    }

    @Override
    public Orders getOrdersById(int ordersId) {
        Orders orders = ordersDao.selectById(ordersId);
        return orders;
    }

    @Override
    public void updateByPrimaryKey(Integer id, Orders orders) {
        orders.setId(id);
        this.ordersDao.updateByPrimaryKey(orders);

    }

    @Override
    public void deleteOrdersByPrimaryKeys(int id) {
        ordersDao.deleteByPrimaryKeys(id);
    }

    @Override
    public List<Orders> getPageOrdersByOrders(Long orderNum, String orderInformation, Integer orderState, int pageNum,
                                              int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> orders = ordersDao.getPageOrdersByOrders(orderNum,orderInformation,orderState);
        return orders;
    }

}
