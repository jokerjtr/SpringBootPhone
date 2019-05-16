package com.jtr.phoneshop.pojo;

import java.util.List;

public class pageBean<T> {
     private Integer pageNum;//当前页面
     private Integer pageSize;//当前每一页页面的数目
     private  Integer totalShop;//商品总数
     private  Integer totalPage;//总页数
     private  Integer startIndex;//开始索引
     private List<T> shop;//每页的内容
     private Integer start;//开始的页面
     private Integer end;//结束页面
     public pageBean(Integer pageNum,Integer pageSize,Integer totalShop){
         this.pageNum=pageNum;this.pageSize=pageSize;this.totalShop=totalShop;
         if(totalShop%pageSize==0)this.totalPage=totalShop/pageSize;
         else this.totalPage=totalShop/pageSize+1;
         this.start=1;
         this.end=5;
         //开始索引
         this.startIndex=(pageNum-1)*pageSize;
         //如果页数小于5页
         if(this.totalPage<5){
             this.end=this.totalPage;
         }else{
             this.start=pageNum-5;
             this.end=pageNum+5;
         }
         if(this.start<=0){
             this.start=1;
             this.end=5;
         }
         if(this.end>this.totalPage){
             this.end=this.totalPage;
             this.start=this.end-4;
         }
     }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalShop() {
        return totalShop;
    }

    public void setTotalShop(Integer totalShop) {
        this.totalShop = totalShop;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getShop() {
        return shop;
    }

    public void setShop(List<T> shop) {
        this.shop = shop;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
