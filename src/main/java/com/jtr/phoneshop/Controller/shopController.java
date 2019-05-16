package com.jtr.phoneshop.Controller;

import com.jtr.phoneshop.Service.GoodService;
import com.jtr.phoneshop.pojo.Band;
import com.jtr.phoneshop.pojo.Good;
import com.jtr.phoneshop.pojo.pageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class shopController {
    private pageBean pageBean;
    @Autowired
    private GoodService goodService;
    @Autowired
    private Good good;
    @GetMapping("/toshopshow/{bandid}/{pageNum}")
    public ModelAndView toShopShow(@PathVariable("bandid")Integer bandid,@PathVariable("pageNum")Integer pageNum){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("goodshow");
          if(bandid==0){
              //表示所有产品
              Integer count = goodService.findCount();
              pageBean=new pageBean(pageNum,12,count);
              good.setPageBean(pageBean);
              List<Good> listGoodLimit = goodService.findListGoodLimit(good);
              pageBean.setShop(listGoodLimit);
              modelAndView.addObject("goodlimit",pageBean);
              modelAndView.addObject("pageNum",pageNum);

          }else{
              //表示品牌分页显示
              Integer countByBandId = goodService.findCountByBandId(bandid);
              pageBean=new pageBean(pageNum,12,countByBandId);
              good.setPageBean(pageBean);
              good.setSearch(bandid);
              List<Good> listGoodByBandNameLimit = goodService.findListGoodByBandNameLimit(good);
              pageBean.setShop(listGoodByBandNameLimit);
              modelAndView.addObject("goodlimit",pageBean);
              modelAndView.addObject("pageNum",pageNum);
          }
        List<Band> bandList = goodService.findBandList();
          modelAndView.addObject("bandlist",bandList);
          modelAndView.addObject("bandid",bandid);
          modelAndView.addObject("mod","band");
        return  modelAndView;
    }

    @GetMapping("/findGoodshow/{goodname}/{pageNum}")
    public ModelAndView findGoodshow(@PathVariable("goodname") String goodname,@PathVariable("pageNum") Integer pageNum){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("mod","good");
        if(goodname==null||goodname.equals("")) goodname="华为";
        String name="%"+goodname+"%";
        Integer byGoodLikeCount = goodService.findByGoodLikeCount(name);
        pageBean=new pageBean(pageNum,12,byGoodLikeCount);
        good.setGoodname(name);
        List<Good> byGoodLikeLimit = goodService.findByGoodLikeLimit(good);
        pageBean.setShop(byGoodLikeLimit);
        modelAndView.addObject("goodlimit",pageBean);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("bandid",goodname);
        modelAndView.addObject("showgood",goodname);
        modelAndView.addObject("mod","good");
        List<Band> bandList = goodService.findBandList();
        modelAndView.addObject("bandlist",bandList);
        modelAndView.setViewName("goodshow");
        return modelAndView;
    }
}
