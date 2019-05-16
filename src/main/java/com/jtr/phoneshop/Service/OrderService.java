package com.jtr.phoneshop.Service;

import com.jtr.phoneshop.Mapper.orderMapper;
import com.jtr.phoneshop.pojo.OrderCustor;
import com.jtr.phoneshop.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class OrderService {
     @Autowired
     private  orderMapper orderMapper;
    //生成订单
    public void insertorder(Orders orders){
        orderMapper.insertorder(orders);
    }
   //销售统计

   public List<OrderCustor> findOrdersList(Integer userid){
        return orderMapper.findOrdersList(userid);
   }
   //订单处理

    public List<OrderCustor>findOrdersOperator(){
        return orderMapper.findOrdersOperator();
    }
    ////更新订单转态
      public void updteorderstatus( String orderstatus, String orderid){
        orderMapper.updteorderstatus(orderstatus,orderid);
      }
}
