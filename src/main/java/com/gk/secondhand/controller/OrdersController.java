package com.gk.secondhand.controller;

import com.gk.secondhand.entity.*;
import com.gk.secondhand.service.GoodsService;
import com.gk.secondhand.service.PurseService;
import com.gk.secondhand.service.impl.MessageServiceImpl;
import com.gk.secondhand.service.impl.OrdersServiceImpl;
import com.gk.secondhand.util.DateUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersServiceImpl ordersService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private PurseService purseService;
    @Resource
    private MessageServiceImpl messageService;

    ModelAndView mv = new ModelAndView();

    /**
     * 我的订单 买
     */
    @RequestMapping(value = "/myOrders")
    public ModelAndView orders(HttpServletRequest request) {
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        Integer user_id = cur_user.getId();
        List<Orders> ordersList1=new ArrayList<Orders>();
        List<Orders> ordersList2=new ArrayList<Orders>();
        ordersList1 = ordersService.getOrdersByUserId(user_id);
        ordersList2 = ordersService.getOrdersByUserAndGoods(user_id);
        Purse myPurse=purseService.getPurseByUserId(user_id);
        mv.addObject("ordersOfSell",ordersList2);
        mv.addObject("orders",ordersList1);
        mv.addObject("myPurse",myPurse);
        mv.setViewName("/user/orders");
        return mv;
    }


    /**
     * 提交订单
     */
    @RequestMapping(value = "/addOrders")
    @Transactional
    public String addorders(HttpServletRequest request,Orders orders) {
        Date d=new Date();//获取时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//转换格式
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        Integer user_id = cur_user.getId();
        orders.setUserId(user_id);
        orders.setOrderDate(sdf.format(d));
        Goods goods=new Goods();
        goods.setStatus(0);
        goods.setId(orders.getGoodsId());
        goodsService.updateGoodsByGoodsId(goods);
        ordersService.addOrders(orders);
        Float balance=orders.getOrderPrice();
        purseService.updatePurseOfdel(user_id,balance);
        //获取message需要的时间格式
        String t = DateUtil.getNowDate();
        orders.setGoods(goodsService.getGoodsByPrimaryKey(orders.getGoodsId()));
        Message message=new Message();
        System.out.println(orders.toString());
        message.setUserid(orders.getGoods().getUserId());
        message.setMessage("您的“"+orders.getGoods().getName()+"”物品已有买家购买，请与买家联系进行发货");
        message.setMessageData(t);
        message.setIsRead(0);
        messageService.noticeBuy(message);
        return "redirect:/orders/myOrders";
    }

    /**
     * 发货 根据订单号
     */
    @RequestMapping(value = "/deliver/{orderNum}")
    public String deliver(HttpServletRequest request,@PathVariable("orderNum")Integer orderNum) {

        ordersService.deliverByOrderNum(orderNum);


        return "redirect:/orders/myOrders";
    }



    /**
     * 收货
     */
    @RequestMapping(value = "/receipt")
    public String receipt(HttpServletRequest request) {
        Integer orderNum=Integer.parseInt(request.getParameter("orderNum"));
        Float balance=Float.parseFloat(request.getParameter("orderPrice"));
        Integer goodsId=Integer.parseInt(request.getParameter("goodsId"));
        Integer userId=goodsService.getGoodsById(goodsId).getUserId();
        ordersService.receiptByOrderNum(orderNum);
        purseService.updatePurseByuserId(userId,balance);
        /*买家确认收货后，卖家钱包+*/
        return "redirect:/orders/myOrders";
    }

}
