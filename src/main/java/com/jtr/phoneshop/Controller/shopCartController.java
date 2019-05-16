package com.jtr.phoneshop.Controller;

import com.jtr.phoneshop.Service.GoodService;
import com.jtr.phoneshop.pojo.Good;
import com.jtr.phoneshop.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class shopCartController {
    @Autowired
    private Good goodv;
    @Autowired
    private GoodService goodService;
    //获得cookie
    public Cookie getCookie(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User loginUser =(User) session.getAttribute("loginUser");
        Cookie cookie[]=request.getCookies();
        Cookie mycookie=null;
        for(Cookie cook:cookie){
            if(loginUser.getUserid().toString().equals(cook.getName())){
                  mycookie=cook;
            }
        }
        return mycookie;
    }
    //设置cookie的value值 属性与属性用&隔开，对象与对象用==分割
    public String setCookieValue(List<Good> goodlist)
    {
         StringBuffer goods=new StringBuffer();
         for(Good good:goodlist){
             goods.append(good.getGoodname()+"&"+
                     good.getGoodimg()+"&"+good.getGoodram()+"&"+good.getGoodcolor()+"&" +
                     good.getGoodnumber()+"&"+good.getGoodprice()+"&"+good.getGoodid()+"==");
         }
         return  goods.toString().substring(0,goods.toString().length());
    }
   //获得购物车列表信息
    public List<Good>getGoodList(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        //商品列表
        List<Good> items=new ArrayList<Good>();
        //商品值
        String cookvalue=null;
        //获得cookie
        Cookie cookie=getCookie(request,response);
        if(cookie!=null){
            cookvalue= URLDecoder.decode(cookie.getValue(),"utf-8");
            if(cookvalue!=null&&!"".equals(cookvalue)){
                String []goodt=cookvalue.split("==");
                for(String go:goodt){
                    String []arr=go.split("&");
                    Good list=new Good();
                    list.setGoodname(arr[0]);
                    list.setGoodimg(arr[1]);
                    list.setGoodram(arr[2]);
                    list.setGoodcolor(arr[3]);
                    Integer number=Integer.parseInt(arr[4]);
                    list.setGoodnumber(number);
                    Double money=Double.parseDouble(arr[5]);
                    Integer goodid=Integer.parseInt(arr[6]);
                    list.setGoodid(goodid);
                    list.setGoodprice(money);
                    items.add(list);
                }
            }
        }
        return items;
    }
    //对购物车的增删查改
    @RequestMapping("/deletegood")
    public ModelAndView deletegood(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        List<Good>itmes=null;
        String id=request.getParameter("goodid");
        Integer goodi=Integer.parseInt(id);
        itmes=getGoodList(request,response);
        Good deleteGood=null;
        for(Good g:itmes){
            if(g.getGoodid().equals(goodi)){
                deleteGood=g;
                break;
            }
        }
        if(deleteGood!=null){
            itmes.remove(deleteGood);
        }
        Cookie cookie = getCookie(request, response);
        cookie.setValue(URLEncoder.encode(setCookieValue(itmes),"utf-8"));
        cookie.setMaxAge(60*60*24*31);
        response.addCookie(cookie);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/toshopcard");
        return modelAndView;

    }
    //清空购物车
    @RequestMapping("/deleteAllcookie")
    public ModelAndView deleteAllcookie(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = getCookie(request, response);
        cookie.setValue(null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/toshopcard");
        return modelAndView;
    }
    //去购物车页面
    @RequestMapping("/toshopcard")
    public ModelAndView toshopcard(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        List<Good> goodList =null;
        goodList=getGoodList(request, response);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("goodlist",goodList);
        modelAndView.setViewName("shopcard");
        return modelAndView;
    }
    @RequestMapping("/addshopcard")
    public ModelAndView addshopcard(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        Cookie cookie = getCookie(request, response);
        List<Good> goodList = getGoodList(request, response);
        String goodname=request.getParameter("goodname");
        String goodram=request.getParameter("goodram");
        String goodcolor=request.getParameter("goodcolor");
        String goodimg=request.getParameter("imgurl");
        String money=request.getParameter("goodprice");
        Double goodprice=Double.parseDouble(money);
        String number=request.getParameter("goodnumber");
        Integer goodnumber=Integer.parseInt(number);
        Integer goodid = goodService.findGoodIdByGoodnameAndGoodRamAndGoodcolor(goodname, goodram, goodcolor);
        goodv.setGoodid(goodid);
        goodv.setGoodname(goodname);
        goodv.setGoodram(goodram);
        goodv.setGoodcolor(goodcolor);
        goodv.setGoodnumber(goodnumber);
        goodv.setGoodprice(goodprice);
        goodv.setGoodimg(goodimg);
        if(goodList.size()<=0){
            //表示购物车列表为空
            goodList.add(goodv);
            //如果列表为空可能存在没有创建cookie
            if(cookie==null){
                HttpSession session = request.getSession();
                User loginUser = (User)session.getAttribute("loginUser");

                cookie=new Cookie(loginUser.getUserid().toString(),URLEncoder.encode(setCookieValue(goodList),"utf-8"));
                cookie.setMaxAge(60*60*24*31);
                response.addCookie(cookie);
            }else{
                //这个cookie以创建当时值value为Null
                cookie.setMaxAge(60*60*24*31);
                cookie.setValue(URLEncoder.encode(setCookieValue(goodList),"utf-8"));
                response.addCookie(cookie);
            }
        }else{
            //表示列表中已经存在商品了
            boolean iseixt=false;
            int i=0;
            for(Good good:goodList){
                int j=i++;
                if(good.getGoodid().equals(goodid)){
                    good.setGoodnumber(good.getGoodnumber()+goodnumber);
                    goodList.remove(j);
                    goodList.add(j,good);
                    iseixt=true;break;
                }
            }
            if(!iseixt){
                goodList.add(goodv);
            }
            cookie.setMaxAge(60*60*24*31);
            cookie.setValue(URLEncoder.encode(setCookieValue(goodList),"utf-8"));
            response.addCookie(cookie);
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/toindex");
        return modelAndView;
    }
}
