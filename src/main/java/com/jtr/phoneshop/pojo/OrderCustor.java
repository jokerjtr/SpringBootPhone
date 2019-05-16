package com.jtr.phoneshop.pojo;
/*
* 订单代理类，用于销售统计
*
* */

public class OrderCustor {
    private String orderid;//订单编号
    private Integer goodid;//商品编号
    private String  goodname;//商品名称
    private String goodram;//手机内存
    private  String goodcolor;//手机颜色
    private  Integer goodtotal;//销售数量
    private Double totalmoney;//总价
    private  String addressid;//收获地址编号
    private String addressname;//收获定制姓名
    private String addressphone;//收获地址电话
    private String addr;//收获地址
    private Integer userid;//用户编号
    private  Integer bandid;//品牌编号
    private String bandname;//品牌名
    private Integer goodnumber;//手机库存
    private  String orderstatus;//订单转态
    private pageBean pageBean;
    private Integer asc;//0表示降序，1，表示升序

    public com.jtr.phoneshop.pojo.pageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(com.jtr.phoneshop.pojo.pageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Integer getAsc() {
        return asc;
    }

    public void setAsc(Integer asc) {
        this.asc = asc;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getGoodram() {
        return goodram;
    }

    public void setGoodram(String goodram) {
        this.goodram = goodram;
    }

    public String getGoodcolor() {
        return goodcolor;
    }

    public void setGoodcolor(String goodcolor) {
        this.goodcolor = goodcolor;
    }

    public Integer getGoodtotal() {
        return goodtotal;
    }

    public void setGoodtotal(Integer goodtotal) {
        this.goodtotal = goodtotal;
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public String getAddressphone() {
        return addressphone;
    }

    public void setAddressphone(String addressphone) {
        this.addressphone = addressphone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBandid() {
        return bandid;
    }

    public void setBandid(Integer bandid) {
        this.bandid = bandid;
    }

    public String getBandname() {
        return bandname;
    }

    public void setBandname(String bandname) {
        this.bandname = bandname;
    }

    public Integer getGoodnumber() {
        return goodnumber;
    }

    public void setGoodnumber(Integer goodnumber) {
        this.goodnumber = goodnumber;
    }
}
