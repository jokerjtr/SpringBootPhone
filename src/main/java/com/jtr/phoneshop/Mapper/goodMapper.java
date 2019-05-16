package com.jtr.phoneshop.Mapper;

import com.jtr.phoneshop.pojo.Band;
import com.jtr.phoneshop.pojo.Good;
import com.jtr.phoneshop.pojo.OrderCustor;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品
 */
@Mapper
public interface goodMapper {
    //更具品牌的英文民来显示商品ajax
    @Select("SELECT good.goodid,good.goodname,good.goodram,good.goodprice,good.goodcolor,good.goodstatus,good.goodimg,good.bandid,good.goodnumber\n" +
            "FROM good,band WHERE good.bandid=band.bandid and band.banden=#{banden} LIMIT 0,10")
    public List<Good>findListGoodByBandEn(String banden);
    //手机名来找手机产品
    @Select("SELECT * FROM good WHERE good.goodname=#{goodname}")
    public List<Good>findListGoodByGoodName(String goodname);
    //按手机名找手机color
    @Select("SELECT DISTINCT(goodcolor) FROM good WHERE good.goodname=#{goodname}")
    public List<String>findListColorByGoodName(String goodname);
    //按手机名找手机ram
    @Select("SELECT DISTINCT(goodram) FROM good WHERE good.goodname=#{goodname}")
    public List<String>findListRamByGoodName(String goodname);
    //根据手机名，手机颜色，手机ram来确定价格
    @Select("SELECT goodprice FROM good WHERE good.goodname=#{goodname} and good.goodram=#{goodram} and good.goodcolor=#{goodcolor}")
    public Double findMoneyByGoodNameAndGoodColorAndGoodRam(@Param("goodname")String goodname,@Param("goodram") String goodram,@Param("goodcolor") String goodcolor);
    //根据手机名和手机颜色
    @Select("SELECT good.goodimg FROM good WHERE good.goodname=#{goodname} and good.goodcolor=#{goodcolor} LIMIT 0,1")
    public String findGoodimgByGoodNameAndGoodColor(@Param("goodname") String goodname,@Param("goodcolor") String goodcolor);
    //更具手机名和手机颜色手机内存来找到相对应确定id
    @Select("SELECT goodid FROM good WHERE goodname=#{goodname} and goodram=#{goodram} and goodcolor=#{goodcolor}")
    public Integer findGoodIdByGoodnameAndGoodRamAndGoodcolor(@Param("goodname")String goodname,@Param("goodram")String goodram,@Param("goodcolor")String goodcolor);

    //将全部的手机产品进行页面的显示
    @Select("SELECT good.* FROM good,band WHERE good.bandid=band.bandid  LIMIT #{pageBean.startIndex},#{pageBean.pageSize}")
    public List<Good>findListGoodLimit(Good good);
    //按照品牌分页显示
    @Select("SELECT good.* FROM good,band WHERE good.bandid=band.bandid and band.bandid=#{search} LIMIT #{pageBean.startIndex},#{pageBean.pageSize}")
    public List<Good>findListGoodByBandName(Good good);

    //查找所有商品的总数
    @Select("SELECT count(*) FROM good")
    public Integer findCount();
    //查询品牌总数
    @Select("SELECT count(*) FROM good WHERE bandid=#{bandid}")
    public Integer findCountByBandId(Integer bandid);

    // 查找品牌
    @Select("SELECT * FROM band ")
    public List<Band>findBandList();

    //按照手机名模糊查询
    @Select("SELECT good.* FROM good WHERE goodname LIKE #{goodname} LIMIT #{pageBean.startIndex},#{pageBean.pageSize}" )
    public List<Good>findByGoodLikeLimit(Good good);
    //找手机模糊名总数
    @Select("SELECT count(*) FROM good WHERE goodname LIKE #{goodname}")
    public Integer findByGoodLikeCount(String goodname);

    //手机品牌上架
    @Insert("INSERT INTO band(bandname,banden) VALUES(#{bandname},#{banden})")
    public void inserBand(Band band);

    //手机上架
    @Insert("INSERT INTO good(goodname,goodram,goodcolor,goodimg,goodprice,goodnumber,goodstatus,bandid) VALUES(#{goodname},#{goodram},#{goodcolor},#{goodimg},#{goodprice},#{goodnumber},#{goodstatus},#{bandid})")
    public void insergood(Good good);

    //销售统计，所有手机按照升序
    @Select("SELECT good.goodid,good.goodname,good.goodram,good.goodcolor,band.bandname,count(orders.goodtotal) as goodnumber FROM orders,band,good WHERE orders.goodid=good.goodid and good.bandid=band.bandid and orders.orderstatus=1 GROUP BY orders.goodid ORDER BY goodnumber asc  LIMIT #{pageBean.startIndex},#{pageBean.pageSize}")
    public List<OrderCustor>findOrderOperByGoodASC(OrderCustor orderCustor);
    //销售统计，所有手机按照降序
    @Select("SELECT good.goodid,good.goodname,good.goodram,good.goodcolor,band.bandname,count(orders.goodtotal) as goodnumber FROM orders,band,good WHERE orders.goodid=good.goodid and good.bandid=band.bandid and orders.orderstatus=1 GROUP BY orders.goodid  LIMIT #{pageBean.startIndex},#{pageBean.pageSize}")
    public List<OrderCustor>findOrderOperByGood(OrderCustor orderCustor);
    //手机品牌升序序
    @Select("SELECT good.goodid,good.goodname,good.goodram,good.goodcolor,band.bandname,count(orders.goodtotal) as goodnumber FROM orders,band,good WHERE orders.goodid=good.goodid and good.bandid=band.bandid and band.bandid=#{bandid} and orders.orderstatus=1 GROUP BY orders.goodid ORDER BY goodnumber asc LIMIT #{pageBean.startIndex},#{pageBean.pageSize}")
    public List<OrderCustor>findOrderOperByBandASC(OrderCustor orderCustor);
    //手机品牌降序序
    @Select("SELECT good.goodid,good.goodname,good.goodram,good.goodcolor,band.bandname,count(orders.goodtotal) as goodnumber FROM orders,band,good WHERE orders.goodid=good.goodid and good.bandid=band.bandid and band.bandid=#{bandid} and orders.orderstatus=1 GROUP BY orders.goodid  LIMIT #{pageBean.startIndex},#{pageBean.pageSize}")
    public List<OrderCustor>findOrderOperByBand(OrderCustor orderCustor);

    //更新数量
    @Update("UPDATE good set goodnumber=goodnumber-#{goodtotal} where goodid=#{goodid}")
    public void updategoodnumber(@Param("goodtotal") Integer goodtotal,@Param("goodid") Integer goodid);

    //手机下架
    @Delete("DELETE FROM good where goodid=#{goodid}")
    public void deletegood(Integer goodid);

    //手机实现季度查询
    @Select("SELECT good.goodid,good.goodname,good.goodram,good.goodcolor,count(goodtotal) as number,band.bandname,count(orders.goodtotal) as goodnumber\n" +
            "FROM orders,good,band where orders.goodid=good.goodid and \n" +
            "orders.orderdate and MONTH(orders.orderdate)=#{datetime} and good.bandid=band.bandid and orders.orderstatus=1\n" +
            "GROUP BY good.goodid")
    public List<OrderCustor> findByTimeASC(@Param("datetime") Integer datetime);

}
