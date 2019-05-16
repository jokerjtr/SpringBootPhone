package com.jtr.phoneshop.Mapper;

import com.jtr.phoneshop.pojo.OrderCustor;
import com.jtr.phoneshop.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface orderMapper {
       //生成订单
    @Insert("INSERT INTO orders(orderid,userid,goodid,goodtotal,totalmoney,addressid,ordertext,orderstatus,sendtype) VALUES(#{orderid},#{userid},#{goodid},#{goodtotal},#{totalmoney},#{addressid},#{ordertext},#{orderstatus},#{sendtype})")
       public void insertorder(Orders orders);
    //销售统计
    @Select("SELECT orders.orderid,good.goodid,good.goodname,good.goodram,good.goodcolor,orders.goodtotal,orders.totalmoney,orders.addressid,address.addressname,address.addressphone,address.addr,orders.userid,band.bandid,band.bandname,good.goodnumber,orders.orderstatus FROM orders,good,address,band WHERE orders.goodid=good.goodid and orders.addressid=address.addressid and good.bandid=band.bandid and orders.userid=#{userid}")
    public List<OrderCustor>findOrdersList(Integer userid);

    //订单处理
    @Select("SELECT orders.orderid,good.goodid,good.goodname,good.goodram,good.goodcolor,orders.goodtotal,orders.totalmoney,orders.addressid,address.addressname,address.addressphone,address.addr,orders.userid,band.bandid,band.bandname,good.goodnumber,orders.orderstatus FROM orders,good,address,band WHERE orders.goodid=good.goodid and orders.addressid=address.addressid and good.bandid=band.bandid and orders.orderstatus=0")
    public List<OrderCustor>findOrdersOperator();
    //更新订单转态
    @Update("UPDATE orders SET orderstatus=#{orderstatus} WHERE orderid=#{orderid}")
    public void updteorderstatus(@Param("orderstatus") String orderstatus,@Param("orderid") String orderid);
}
