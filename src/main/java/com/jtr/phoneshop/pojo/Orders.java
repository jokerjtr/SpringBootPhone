package com.jtr.phoneshop.pojo;

public class Orders {
     private String orderid;
     private Integer userid;
     private Integer goodid;
     private  Integer goodtotal;
     private   String addressid;
     private Double totalmoney;
     private String ordertext;
     private String orderstatus;//订单转态 0：表示计较，1表示审核通过，2表示审核不通过，3表示订单接收
     private String sendtype;//支付状态 0表线上，1表货到付款

      private Good good;
      private  Address addr;
    public String getSendtype() {
        return sendtype;
    }

    public void setSendtype(String sendtype) {
        this.sendtype = sendtype;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getOrdertext() {
        return ordertext;
    }

    public void setOrdertext(String ordertext) {
        this.ordertext = ordertext;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getGoodtotal() {
        return goodtotal;
    }

    public void setGoodtotal(Integer goodtotal) {
        this.goodtotal = goodtotal;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }
}
