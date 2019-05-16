package com.jtr.phoneshop.Controller;

import com.jtr.phoneshop.Service.GoodService;
import com.jtr.phoneshop.Service.IndexService;
import com.jtr.phoneshop.pojo.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class indexController {
    @Autowired
    private IndexService indexservice;
    @Autowired
    private GoodService goodService;
    @GetMapping("/toindex")
    public ModelAndView toindex(){
        List<Good> goodStatus = indexservice.findGoodStatus();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("goodlist",goodStatus);
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
    //主页点击商品的英文名来显示效果ajax
    @GetMapping("/findByBanden")
    @ResponseBody
    public List<Good> findByBandEn(HttpServletRequest request, HttpServletResponse response)
    {
           String banden=request.getParameter("banden");

        List<Good> listGoodByBandEn = goodService.findListGoodByBandEn(banden);

        return  listGoodByBandEn;
    }
    @GetMapping("/tomay")
    //点击商品去购买页面
    public ModelAndView tomay(HttpServletRequest request,HttpServletResponse response){
        String imgurl=request.getParameter("imgurl");
        String goodname=request.getParameter("goodname");
        String money=request.getParameter("goodprice");
        Double goodPrice=Double.parseDouble(money);
        List<Good> listGoodByGoodName = goodService.findListGoodByGoodName(goodname);
        List<String> listColorByGoodName = goodService.findListColorByGoodName(goodname);
        List<String> listRamByGoodName = goodService.findListRamByGoodName(goodname);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("imgurl",imgurl);
        modelAndView.addObject("goodname",goodname);
        modelAndView.addObject("goodprice",goodPrice);
        modelAndView.addObject("tomaygoods",listGoodByGoodName);
        modelAndView.addObject("tomaycolor",listColorByGoodName);
        modelAndView.addObject("tomayram",listRamByGoodName);
        modelAndView.setViewName("tomay");
        return modelAndView;
    }
    //更具手机名和手机颜色和手机ram来确定价格
    @GetMapping("findMoney")
    @ResponseBody
    public String findMoney(HttpServletRequest request,HttpServletResponse response){
                String goodname=request.getParameter("goodname");
                String goodcolor=request.getParameter("goodcolor");
                String goodram=request.getParameter("goodram");
        Double moneyByGoodNameAndGoodColorAndGoodRam = goodService.findMoneyByGoodNameAndGoodColorAndGoodRam(goodname, goodram, goodcolor);
        if(moneyByGoodNameAndGoodColorAndGoodRam==null)moneyByGoodNameAndGoodColorAndGoodRam=0.0;
        return Double.toString(moneyByGoodNameAndGoodColorAndGoodRam);
    }
    //根据手机名和手机颜色来显示图片
    @GetMapping("/findimg")
    @ResponseBody
    public String findimg(HttpServletRequest request,HttpServletResponse response){
                  String goodname=request.getParameter("goodname");
                  String goodcolor=request.getParameter("goodcolor");
        String goodimgByGoodNameAndGoodColor = goodService.findGoodimgByGoodNameAndGoodColor(goodname, goodcolor);
        return goodimgByGoodNameAndGoodColor;
    }
}
