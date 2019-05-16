package com.jtr.phoneshop.Service;

import com.jtr.phoneshop.Mapper.goodMapper;
import com.jtr.phoneshop.pojo.Band;
import com.jtr.phoneshop.pojo.Good;
import com.jtr.phoneshop.pojo.OrderCustor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GoodService {
    @Autowired
    private goodMapper goodMapper;
    //更具英文名来寻找商品
    public List<Good>findListGoodByBandEn(String banden){
        List<Good> listGoodByBandEn = goodMapper.findListGoodByBandEn(banden);
        return listGoodByBandEn;
    }
    //手机名来找手机产品
    public List<Good>findListGoodByGoodName(String goodname)
    {
        List<Good> listGoodByGoodName = goodMapper.findListGoodByGoodName(goodname);
        return listGoodByGoodName;
    }
    ////按手机名找手机color

      public List<String>findListColorByGoodName(String goodname){
          List<String> listColorByGoodName = goodMapper.findListColorByGoodName(goodname);
          return listColorByGoodName;
      }
      //按手机名找手机ram

      public List<String>findListRamByGoodName(String goodname){
          List<String> listRamByGoodName = goodMapper.findListRamByGoodName(goodname);
          return listRamByGoodName;
      }
      // //根据手机名，手机颜色，手机ram来确定价格

        public Double findMoneyByGoodNameAndGoodColorAndGoodRam(String goodname, String goodram, String goodcolor){
            Double moneyByGoodNameAndGoodColorAndGoodRam = goodMapper.findMoneyByGoodNameAndGoodColorAndGoodRam(goodname, goodram, goodcolor);
            return moneyByGoodNameAndGoodColorAndGoodRam;
        }
        ////根据手机名和手机颜色
    public String findGoodimgByGoodNameAndGoodColor(String goodname,String goodcolor){
        String goodimgByGoodNameAndGoodColor = goodMapper.findGoodimgByGoodNameAndGoodColor(goodname, goodcolor);
        return goodimgByGoodNameAndGoodColor;
    }
    //更具手机名和手机颜色手机内存来找到相对应确定id
    public Integer findGoodIdByGoodnameAndGoodRamAndGoodcolor(String goodname,String goodram,String goodcolor){
        Integer goodIdByGoodnameAndGoodRamAndGoodcolor = goodMapper.findGoodIdByGoodnameAndGoodRamAndGoodcolor(goodname, goodram, goodcolor);
        return goodIdByGoodnameAndGoodRamAndGoodcolor;
    }

    //   //将全部的手机产品进行页面的显示

       public List<Good>findListGoodLimit(Good good){
           List<Good> listGoodLimit = goodMapper.findListGoodLimit(good);
           return listGoodLimit;
       }
    //    //按照品牌分页显示
       public List<Good>findListGoodByBandNameLimit(Good good){
           List<Good> listGoodByBandName = goodMapper.findListGoodByBandName(good);
           return  listGoodByBandName;
       }
       //查找所有商品的总数

        public Integer findCount(){
            return goodMapper.findCount();
        }
       //查询品牌总数
       public Integer findCountByBandId(Integer bandid){
          return goodMapper.findCountByBandId(bandid);
       }
       //// 查找品牌
      public List<Band>findBandList(){
       return goodMapper.findBandList();
      }
      // //按照手机名模糊查询
        public List<Good>findByGoodLikeLimit(Good good){
            return goodMapper.findByGoodLikeLimit(good);
        }
      //找手机模糊名总数
      public Integer findByGoodLikeCount(String goodname){
          return goodMapper.findByGoodLikeCount(goodname);
      }

      //手机品牌上架
    public void inserBand(Band band){
         goodMapper.inserBand(band);
    }


     public void insergood(Good good){
        goodMapper.insergood(good);
     }
     //销售统计，所有手机按照降序
    public List<OrderCustor>findOrderOperByGoodASC(OrderCustor orderCustor){
          return  goodMapper.findOrderOperByGoodASC(orderCustor);
    }
    //    //销售统计，所有手机按照升序
    public List<OrderCustor>findOrderOperByGood(OrderCustor orderCustor){
         return goodMapper.findOrderOperByGood(orderCustor);
    }
    //    //手机品牌降序
      public List<OrderCustor>findOrderOperByBandASC(OrderCustor orderCustor){
        return goodMapper.findOrderOperByBandASC(orderCustor);
      }
     //手机品牌升序
     public List<OrderCustor>findOrderOperByBand(OrderCustor orderCustor){
        return goodMapper.findOrderOperByBand(orderCustor);
     }
     ////更新数量
    public void updategoodnumber( Integer goodtotal, Integer goodid){
        goodMapper.updategoodnumber(goodtotal,goodid);
    }

    //手机下架

    public void deletegood(Integer goodid){
        goodMapper.deletegood(goodid);
    }

    //手机实现季度查询
    public List<OrderCustor> findByTimeASC(Integer datatime){
        List<OrderCustor> byTimeASC = goodMapper.findByTimeASC(datatime);
        return byTimeASC;
    }
}
