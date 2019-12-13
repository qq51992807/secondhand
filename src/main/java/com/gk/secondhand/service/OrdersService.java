package com.gk.secondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.secondhand.entity.Orders;

import java.util.List;

public interface OrdersService extends IService<Orders> {
    public List<Orders> getOrdersByUserId(Integer user_id);

    public List<Orders> getOrdersByUserAndGoods(Integer user_id);

    public void addOrders(Orders orders);

    public void deliverByOrderNum(Integer orderNum);

    public void receiptByOrderNum(Integer orderNum);

    public int getOrdersNum();

    public List<Orders> getPageOrders(int pageNum, int pageSize);

    public Orders getOrdersById(int ordersId);


    public void updateByPrimaryKey(Integer id, Orders orders);

    public void deleteOrdersByPrimaryKeys(int parseInt);

    public List<Orders> getPageOrdersByOrders(Long orderNum, String orderInformation, Integer orderState, int pageNum,
                                              int pageSize);

}
