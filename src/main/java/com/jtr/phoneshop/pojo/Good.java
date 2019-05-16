package com.jtr.phoneshop.pojo;

public class Good {
    private  Integer goodid;
    private  String goodname;
    private String goodram;
    private String goodcolor;
    private String goodimg;
    private Double goodprice;
    private Integer goodnumber;
    private Integer goodstatus;
    private Integer bandid;

    private  pageBean pageBean;
    private Integer search;

    public Integer getSearch() {
        return search;
    }

    public void setSearch(Integer search) {
        this.search = search;
    }

    public com.jtr.phoneshop.pojo.pageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(com.jtr.phoneshop.pojo.pageBean pageBean) {
        this.pageBean = pageBean;
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

    public String getGoodimg() {
        return goodimg;
    }

    public void setGoodimg(String goodimg) {
        this.goodimg = goodimg;
    }

    public Double getGoodprice() {
        return goodprice;
    }

    public void setGoodprice(Double goodprice) {
        this.goodprice = goodprice;
    }

    public Integer getGoodnumber() {
        return goodnumber;
    }

    public void setGoodnumber(Integer goodnumber) {
        this.goodnumber = goodnumber;
    }

    public Integer getGoodstatus() {
        return goodstatus;
    }

    public void setGoodstatus(Integer goodstatus) {
        this.goodstatus = goodstatus;
    }

    public Integer getBandid() {
        return bandid;
    }

    public void setBandid(Integer bandid) {
        this.bandid = bandid;
    }
}
